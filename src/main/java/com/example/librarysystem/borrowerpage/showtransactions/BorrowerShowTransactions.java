package com.example.librarysystem.borrowerpage.showtransactions;

import Accounts.Borrower;
import Accounts.Customer;
import Orders.Order;
import Orders.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import System.Library;

public class BorrowerShowTransactions implements Initializable {

    @FXML
    private GridPane transactionCardsGridLayout;

    private Borrower borrower = Library.logged_in_borrower();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;
        try{
            int transaction_no = 1;
            for (Transaction transaction : borrower.getTransactionsHistory()){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewtransactions_page/borrower_transactioncard.fxml"));

                VBox transactionCard = fxmlLoader.load();
                // calling the controller of the loaded fxml , we create instance "object" from it
                BorrowerTransactionCard transactionCardController = fxmlLoader.getController();
                transactionCardController.setTransaction(transaction);
                transactionCardController.setData(transaction_no);

                if (column == 3){
                    column = 0;
                    ++row;
                }

                transactionCardsGridLayout.add(transactionCard,column++,row);
                GridPane.setMargin(transactionCard,new Insets(10));
                transaction_no++;
            }
        }
        catch (IOException exception){
            System.out.println(exception);

        }

    }

}
