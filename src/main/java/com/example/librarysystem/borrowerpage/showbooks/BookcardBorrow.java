package com.example.librarysystem.borrowerpage.showbooks;

import Accounts.Borrower;
import Book.Book;
import com.example.librarysystem.borrowerpage.BorrowerHomepage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import System.Library;
import Accounts.Borrower;
import Orders.BorrowerCart;
import Orders.Transaction;
import javafx.scene.layout.Pane;

public class BookcardBorrow {

    @FXML
    private Label bookName;

    @FXML
    private Button bookViewmore;

    @FXML
    private Label bookauthor;

    @FXML
    private ImageView bookimg;


    @FXML
    private Button borrowBook_btn;

    private Book book;

    public void setBook(Book book){ // passing data between controller we will setBook first
        this.book = book;
    }

    public void setData (){
        Image bookImage = new Image(book.getBook_image());
        bookimg.setImage(bookImage);
        bookName.setText(book.getBook_Title());
        bookauthor.setText(book.getAuthor());
    }

    @FXML
    void viewMore(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewbooks_page/book_moreinfo.fxml"));
        Pane mainPanel = null;
        try {
            mainPanel = fxmlLoader.load();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // ew3a t3ml import l class BookMoreInfo ely fy customer 3shan homa eltnen bnfs elesm yfrk package bs fa kda ytlghbt
        BookMoreInfo bookMoreInfoController = fxmlLoader.getController();


        bookMoreInfoController.setBook(book);
        try{
            bookMoreInfoController.setData();
        }catch(IOException exception){
            exception.printStackTrace();
        }

        if (book.getStatus()) {
            bookMoreInfoController.showBorrowBtn();
        } else {
            bookMoreInfoController.showReserveBtn();
        }
        BorrowerHomepage.changeContentStackPane(mainPanel);

    }

    public void onBorrow(ActionEvent event){
        Borrower logged_bororwer = Library.logged_in_borrower();
        if (logged_bororwer != null){
            logged_bororwer.shoppingCart.addToCart(book);
        }


    }

}
