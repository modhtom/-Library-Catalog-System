package com.example.librarysystem.customerpage.showcart;

import com.example.librarysystem.Starter;
import com.example.librarysystem.customerpage.CustomerHomepage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import System.*;
import Accounts.Customer;
import Book.Book;
public class BookCardCart implements Initializable {

    @FXML
    private Label bookCategory;

    @FXML
    private Label bookName;

    @FXML
    private Spinner<Integer> bookQuantity;

    @FXML
    private ImageView bookimg;

    @FXML
    private Label bookprice;

    @FXML
    private Button removeBook_btn;

    private Book book; // 3shan lma n3mlha calling nb3tlha object elktab w hya t3ml set l data


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // limits of spin box , initial value
        bookQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,1));
        //bookQuantity.setEditable(true);
        bookQuantity.valueProperty().addListener((obs, oldValue, newValue) -> {
                    Customer customer = Library.logged_in_customer();
                    if (customer != null){
                        if(!customer.shoppingCart.updateQuantity(book,newValue)){ // that means it will execute function but if that retruns false so not will make it true so the body executes
                            Platform.runLater(() -> {
                                Starter.showAlert(Alert.AlertType.ERROR, "Error", "Invalid data", "We don't have that quantity in stock");
                            });
                            return;
                        }
                    }

        });


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
        bookprice.setText("$" + book.getPriceBuying());

    }

    public void removeBook(ActionEvent event){
        Customer customer = Library.logged_in_customer();
        if (customer != null){
            customer.shoppingCart.removeFromCart(book);
            try {
                Pane cartPage= FXMLLoader.load(getClass().getResource("/com/example/librarysystem/customer_pages/viewcart_page/viewcart_customer.fxml"));
                CustomerHomepage.changeContentStackPane(cartPage);
                //Starter.switchScreen("/com/example/librarysystem/customer_pages/viewcart_page/viewcart_customer.fxml","Cart");
            }
            catch (Exception exception){
                System.out.println("Error in switching screen in removing book");
                exception.printStackTrace();
            }
        }


    }
}
