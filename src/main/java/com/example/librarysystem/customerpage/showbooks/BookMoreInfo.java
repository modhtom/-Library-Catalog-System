package com.example.librarysystem.customerpage.showbooks;

import Accounts.Borrower;
import Accounts.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


import Book.*;

import java.io.IOException;
import System.Library;

public class BookMoreInfo  {

    @FXML
    private Label BookPrice;

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
    private VBox commentSection_VBOX;



    @FXML
    private Button buy_btn;

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
        BookPrice.setText("$" + book.getPriceBuying());
        BookPublication.setText(book.getPublication_year());
        BookRating.setText(book.getRating()+ "/5");
        //showComment();

    }

    public void showComment() throws IOException {

        //System.out.println("There is a review....");
        for (int i = 0 ; i < book.reviews.size();i++){
            System.out.println("There is a review....");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/viewbooks_page/view_bookreview.fxml"));
            VBox eachReviewLayout = fxmlLoader.load();;


            ViewBookReview reviewController = fxmlLoader.getController();
            reviewController.setReview(book.reviews.get(i));
            reviewController.setData();

            commentSection_VBOX.getChildren().add(eachReviewLayout);

            }


    }

    public void onBuy(ActionEvent event){
        Customer logged_customer = Library.logged_in_customer();
        if (logged_customer != null){
            logged_customer.shoppingCart.addToCart(book);
        }
    }

    /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {







    }*/
}
