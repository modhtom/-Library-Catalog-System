package com.example.librarysystem.customerpage.showbooks;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import Book.*;
import Accounts.Customer;
import System.*;

public class ViewBookReview {

    @FXML
    private TextArea customerCommentBook;

    @FXML
    private Label customerName;

    @FXML
    private Label customerRatingBook;


    private Review review;

    public void setReview(Review review){
        this.review = review;

    }
    /*
    public String getCustomerName(){
        Customer customer = Customer.getCustomer(review.getCustomerID());
        if (customer != null){
            return customer.getName();
        }
        return null;

    }*/

    public String getCustomerName(){
        Customer customer = review.getCustomer();
        return customer.getName();
    }

    public void setData(){
        customerName.setText(getCustomerName());
        customerRatingBook.setText(review.getRating() + "/5");
        customerCommentBook.setText(review.getComment());
    }

}
