package com.example.librarysystem.borrowerpage.showbooks;

import Accounts.Borrower;
import Book.Book;
import com.example.librarysystem.Starter;
import com.example.librarysystem.customerpage.showbooks.ViewBookReview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import System.Library;
import Accounts.Borrower;
import Orders.BorrowerCart;
import Orders.Transaction;

public class BookMoreInfo implements Initializable{

    @FXML
    private Label BookPublication;

    @FXML
    private Label BookRating;

    @FXML
    private Label bookAuthor;

    @FXML
    private Label bookCategory;

    @FXML
    private Label bookID;

    @FXML
    private Label bookName;

    @FXML
    private ImageView bookimg;

    @FXML
    private Button borrowBook_btn;

    @FXML
    private VBox commentSection_VBOX;

    @FXML
    private Button reserveBook_btn;


    private Book book;

    public void setBook(Book book){
        this.book = book;
    }

    public void setData() throws IOException {
        Image bookImage = new Image(book.getBook_image());
        bookimg.setImage(bookImage);
        bookName.setText(book.getBook_Title());
        bookAuthor.setText(book.getAuthor());
        bookID.setText(book.getBook_ID());
        bookCategory.setText(book.getCategory());
        BookPublication.setText(book.getPublication_year());
        BookRating.setText(book.getRating()+ "/5");

        showComment();
    }

    public void showComment() throws IOException {
        for (int i = 0 ; i < book.reviews.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/viewbooks_page/view_bookreview.fxml"));
            VBox eachReviewLayout = fxmlLoader.load();;


            ViewBookReview reviewController = fxmlLoader.getController(); // controller of the customer bs it's the same
            reviewController.setReview(book.reviews.get(i));
            reviewController.setData();

            commentSection_VBOX.getChildren().add(eachReviewLayout);

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reserveBook_btn.setVisible(false);
        borrowBook_btn.setVisible(false);
    }

    public void showBorrowBtn(){
        borrowBook_btn.setVisible(true);
        reserveBook_btn.setVisible(false);
    }

    public void showReserveBtn(){
        reserveBook_btn.setVisible(true);
        borrowBook_btn.setVisible(false);
    }

    public void onBorrow(ActionEvent event){
        Borrower logged_bororwer = Library.logged_in_borrower();
        if (logged_bororwer != null){
            logged_bororwer.shoppingCart.addToCart(book);
        }

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


}
