package Book;
import System.*;
import Orders.*;
import Accounts.*;
import Book.*;

import java.util.ArrayList;
import java.util.Date;


public class Review {
    private String customerID; // what if the customer deletes his account and there is no longer this customer id in database

    private String comment;
    private float rating;

    public Review(String customerID, String comment, float rating ) {
        this.customerID = customerID;
        this.comment = comment;
        this.rating = rating;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Customer getCustomer() {

        return Admin.getCustomerInformation(customerID);
    }




    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String toString(){
        return this.customerID + "," + this.comment + "," + this.rating;
    }

}
