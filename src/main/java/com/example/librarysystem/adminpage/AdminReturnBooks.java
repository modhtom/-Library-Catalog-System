package com.example.librarysystem.adminpage;

import Accounts.Admin;
import System.Library;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.example.librarysystem.Starter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Book.Book;
public class AdminReturnBooks implements Initializable {

    @FXML
    private Label admin_name;
    @FXML
    private TextField BorrowerID;
    @FXML
    private TextField TransactionID;
    @FXML
    private TextField BookID;
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private Group pay;

    @FXML
    private Label fine;
    @FXML
    private Label total;
    @FXML
    private Button proceed;
    @FXML
    private Group bye;

    @FXML
    private VBox id;
    String borrowerID;

    String transactionID;

    String bookID;
    boolean found =false;
    public void submit(ActionEvent event){
        try{
            borrowerID= BorrowerID.getText();
            transactionID= TransactionID.getText();
            bookID= BookID.getText();
            Admin A = Library.logged_in_admin();
            if(A!=null){
                double fine = A.returningBooks(borrowerID,transactionID,bookID);
                if(fine>0) {
                    label.setText("Returning Transaction number: " + transactionID);
                    pay.setOpacity(1);
                    this.fine.setText(String.valueOf(fine));
                    found=true;
                } else if (fine==-1) {
                    label.setText("No Borrower with this id"); // HERE THE ERROR
                } else{
                    label.setText("Returning Transaction number: " + transactionID);
                    pay.setOpacity(1);
                    this.fine.setText("0");
                    found=true;


                    Book book = Library.findBookByID(bookID);
                    book.setStock(book.getStock() + 1);
                }
                /*if(!found)
                    label.setText("No available Transaction with this id.");*/
            }
            else
                label.setText("Please Login.");
        }
        catch (NumberFormatException e){
            label.setText("Please Enter numbers only.");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void onLogOut(ActionEvent event) throws IOException {
        Library.logOutAdmin();
        Starter.switchScreen("/com/example/librarysystem/starter_pages/home_page.fxml","Home Page");
    }
    @FXML
    void onBack(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_homepage.fxml","Admin Home");

    }

    public void pay(ActionEvent event){
        pay.setOpacity(0);
        id.setOpacity(0);
        bye.setOpacity(1);
    }
    public void home(ActionEvent event)throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_homepage.fxml","home");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin_name.setText(Library.logged_in_admin().getName());
    }
}
