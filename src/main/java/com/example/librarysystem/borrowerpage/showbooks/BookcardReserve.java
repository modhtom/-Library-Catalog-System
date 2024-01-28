package com.example.librarysystem.borrowerpage.showbooks;

import Book.Book;
import com.example.librarysystem.Starter;
import com.example.librarysystem.borrowerpage.BorrowerHomepage;
import com.example.librarysystem.borrowerpage.showtransactions.BorrowerShowBooksTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import System.Library;
import Accounts.Borrower;
import Orders.BorrowerCart;
import Orders.Transaction;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class BookcardReserve {

    @FXML
    private Label bookName;

    @FXML
    private Button bookViewmore;

    @FXML
    private Label bookauthor;

    @FXML
    private ImageView bookimg;

    @FXML
    private Button reserveBook_btn;

    @FXML
    private Button viewSuggestion_btn;

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

    public void onReserve(ActionEvent event){
        Borrower logged_bororwer = Library.logged_in_borrower();
        if (logged_bororwer != null){
            if(!logged_bororwer.addReserveBook(book.getBook_ID())){ // it executes the function , and if it returns false , it will show the alert
                Starter.showAlert(Alert.AlertType.ERROR,"Error","You added this book before to the reserve list",null);
                return;
            }
        }

    }

    public void onSuggestion(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewbooks_page/viewsuggestion_borrower.fxml")); // main node


        Pane booksSuggestion = fxmlLoader.load(); // main node of the fxml - main layout main panel
        BorrowerHomepage.changeContentStackPane(booksSuggestion);
        /*
        // msh mhtagen n3ml calling l controller 3shan ehna msh hnb3t haga
        ViewBooksSuggestions booksSuggestionController = fxmlLoader.getController();
        booksSuggestionController.setTransactionBooks();
        booksSuggestionController.loadData();*/


    }

}
