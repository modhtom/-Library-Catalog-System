package com.example.librarysystem.borrowerpage.showcart;

import Accounts.Borrower;
import System.Library;
import Book.Book;
import com.example.librarysystem.Starter;
import com.example.librarysystem.borrowerpage.BorrowerHomepage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class BorrowerBookCardCart implements Initializable {

    @FXML
    private Label bookCategory;

    @FXML
    private Label bookName;

    @FXML
    private Spinner<Integer> bookQuantity;

    @FXML
    private DatePicker bookReturnDate;

    @FXML
    private ImageView bookimg;

    @FXML
    private Label bookpriceBorrowing;

    @FXML
    private Button removeBook_btn;

    private Book book; // 3shan lma n3mlha calling nb3tlha object elktab w hya t3ml set l data

    private Borrower borrower = Library.logged_in_borrower();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // limits of spin box , initial value
        bookQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,1));
        //bookQuantity.setEditable(true);
        bookQuantity.valueProperty().addListener((obs, oldValue, newValue) -> {

            if (borrower != null){
                if(newValue != 1){
                    if(!borrower.shoppingCart.updateQuantity(book,newValue)){ // that means it will execute function but if that retruns false so not will make it true so the body executes
                        Platform.runLater(() -> {
                            Starter.showAlert(Alert.AlertType.ERROR, "Error", "Invalid data", "We don't have that quantity in stock");
                        });
                        //Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","We dont have that quantity in the stock");
                        return;
                    }
                }

            }

        });

        // Add a listener to the value property of the DatePicker
        bookReturnDate.valueProperty().addListener((obs, oldValue, newValue) -> updateReturnDate());


    }

    /*public void updateQuantity(){

    }*/

    public void setBook(Book book){
        this.book = book;
    }

    public void setData(){
        Image bookImage = new Image(book.getBook_image());
        bookimg.setImage(bookImage);
        bookName.setText(book.getBook_Title());
        bookCategory.setText(book.getCategory());
        bookReturnDate.setValue(LocalDate.now().plusDays(14));
        book.setPriceBorrowing(LocalDate.now(),bookReturnDate.getValue());
        bookpriceBorrowing.setText("$" + book.getPriceBorrowing());

    }

    public void updateReturnDate(){ // It is linked in scenebuilder on action with date picker , fa ay wkt hoa hyghyr return date elfunction hy7slha execution
        LocalDate return_date = bookReturnDate.getValue();
        if(borrower.shoppingCart.updateDueDate(book,return_date)){
            //bookReturnDate.setValue(LocalDate.now().plusDays(14)); //HERE ERROR
            book.setPriceBorrowing(LocalDate.now(),bookReturnDate.getValue());
            bookpriceBorrowing.setText("$" + book.getPriceBorrowing());

        }else{
            Platform.runLater(() -> {
                Starter.showAlert(Alert.AlertType.ERROR, "Error", "Invalid data", "You have maximum 40 days");
            });
            //Starter.showAlert(Alert.AlertType.ERROR,"Error","You can only borrow the book for maximum 40 days",null);
            return;
        }

    }



    public void removeBook(ActionEvent event){
        if (borrower != null){
            borrower.shoppingCart.removeFromCart(book);
            try {
                Pane cartPage= FXMLLoader.load(getClass().getResource("/com/example/librarysystem/borrower_pages/viewcart_page/viewcart_borrower.fxml"));
                BorrowerHomepage.changeContentStackPane(cartPage);
            }
            catch (Exception exception){
                System.out.println("Error in switching screen in removing book");
                exception.printStackTrace();
            }
        }


    }

}
