package com.example.librarysystem.adminpage;
import Accounts.Borrower;
import Orders.Transaction;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
 /*
public class AdminConfirmBorrowerReturnBook implements Initializable {

    @FXML
    private TableColumn<Transaction, String> bookid_col;

    @FXML
    private TableColumn<Borrower, String> borrowerid_column;

    @FXML
    private TableView<Borrower> borrowertransactionsdetails_table;

    @FXML
    private TableColumn<Transaction, LocalDate> returndatebook_col;

    @FXML
    private TableColumn<Transaction, String> transactionid_column;



    @FXML
    private TableColumn<Transaction, Boolean> isbookreturn_col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borrowerid_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        transactionid_column.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        bookid_col.setCellValueFactory(new PropertyValueFactory<>("booksID"));
        returndatebook_col.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        isbookreturn_col.setCellValueFactory(new PropertyValueFactory<>("isBookReturned"));

        borrowertransactionsdetails_table.getColumns().addAll(borrowerid_column, transactionid_column, bookid_col,returndatebook_col,isbookreturn_col);

        // Populate data (you need to replace this with your actual data)
        ObservableList<Borrower> borrowers = FXCollections.observableArrayList(
                new Borrower("B1", FXCollections.observableArrayList(new Transaction("T1", FXCollections.observableArrayList("B11", "B12")))),
                new Borrower("B2", FXCollections.observableArrayList(new Transaction("T2", FXCollections.observableArrayList("B21", "B22"))))
        );

        borrowertransactionsdetails_table.setItems(borrowers);

    }
}
*/