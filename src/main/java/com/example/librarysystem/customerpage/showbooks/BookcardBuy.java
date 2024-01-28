package com.example.librarysystem.customerpage.showbooks;
import com.example.librarysystem.Starter;
import com.example.librarysystem.customerpage.CustomerHomepage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Book.*;
import Accounts.Customer;
import System.*;
import javafx.scene.layout.Pane;

public class BookcardBuy implements Initializable {

    @FXML
    private Button addBookToCart;

    @FXML
    private Label bookName;

    @FXML
    private Button bookViewmore;

    @FXML
    private Label bookauthor;

    @FXML
    private ImageView bookimg;

    @FXML
    private Label bookprice;

    private Book book;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // we do that cuz addtocart function event and view more needs book object and i can't pass them in parameter
    public void setBook(Book book){ // passing data between controller we will setBook first
        this.book = book;
    }

    public void setData (){
        System.out.println(book);

        //Image bookImage = new Image(getClass().getResourceAsStream(book.getBook_image()));
        Image bookImage = new Image(book.getBook_image());
        bookimg.setImage(bookImage);
        bookName.setText(book.getBook_Title());
        bookauthor.setText(book.getAuthor());
        bookprice.setText("$"+book.getPriceBuying());
    }


    public void viewMore(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/viewbooks_page/book_moreinfo.fxml"));

        System.out.println("FXML Loader: " + fxmlLoader);

        Pane root = fxmlLoader.load();

        BookMoreInfo bookMoreInfo = fxmlLoader.getController();
        System.out.println("BookMoreInfo Controller: " + bookMoreInfo);

        bookMoreInfo.setBook(book);
        bookMoreInfo.setData();
        bookMoreInfo.showComment();
        CustomerHomepage.changeContentStackPane(root);

        //Starter.getPrimaryStage().setScene(new Scene(root,1280,720)); that will make new screen but i want just change the content of stackpane

// Assuming you have a method to switch screens, use the loaded root as the parameter
        //Starter.switchScreen("book_moreinfo.fxml","dsds");


        /*
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("book_moreinfo.fxml"));
        System.out.println("FXML Loader: " + fxmlLoader);
        BookMoreInfo bookMoreInfo = fxmlLoader.getController();
        System.out.println("BookMoreInfo Controller: " + bookMoreInfo);
        bookMoreInfo.setBook(book);
        bookMoreInfo.setData();
        //Starter.switchScreen("book_moreinfo.fxml","Book Info");
*/

    }

    public void addtoCart(ActionEvent event){
        Customer customer = Library.logged_in_customer();
        if (customer != null){
            customer.shoppingCart.addToCart(book);
        }


    }
}
