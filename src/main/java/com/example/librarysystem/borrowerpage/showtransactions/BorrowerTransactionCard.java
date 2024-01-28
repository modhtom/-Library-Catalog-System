package com.example.librarysystem.borrowerpage.showtransactions;

import Book.Book;
import Orders.Order;
import Orders.Transaction;
import com.example.librarysystem.borrowerpage.BorrowerHomepage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class BorrowerTransactionCard {

    @FXML
    private Label TransactionDate_label;

    @FXML
    private Label totalPrice_label;

    @FXML
    private Label transactionID_label;

    @FXML
    private Label transactionNumber_Label;

    @FXML
    private Button viewBooksOfTransaction_btn;

    private Transaction transaction;


    public void setTransaction(Transaction transaction){
        this.transaction = transaction;
    }

    public void setData(int no_ofTransactions){
        transactionNumber_Label.setText("Transaction #" + no_ofTransactions);
        transactionID_label.setText("Transaction ID:" + transaction.getTransactionId());
        totalPrice_label.setText("Total Price: $" + transaction.getTotalPrice());
        TransactionDate_label.setText(String.valueOf(transaction.getBorrowDate())); // casting local date to string

    }

    public void viewBookTransaction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewtransactions_page/borrower_showbookstransaction.fxml")); // main node


        Pane booksTransaction = fxmlLoader.load(); // main node of the fxml - main layout main panel
        BorrowerShowBooksTransaction booksTransactionController = fxmlLoader.getController();


        for (Book book : transaction.getAllBooks()){
            System.out.println(book);
        } // that works well and it printed the name of books but i dont know why the data didnt send to the another controller
        booksTransactionController.setTransactionBooks(transaction.getAllBooks(),transaction.getQuantity(),transaction.getReturnDate(),transaction.getIsBookReturned());
        booksTransactionController.loadData();
        BorrowerHomepage.changeContentStackPane(booksTransaction);

    }

}
