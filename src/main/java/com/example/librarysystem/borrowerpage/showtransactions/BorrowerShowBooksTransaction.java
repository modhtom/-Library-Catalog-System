package com.example.librarysystem.borrowerpage.showtransactions;

import Book.Book;
import com.example.librarysystem.customerpage.showorders.CustomerBookCardOrder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BorrowerShowBooksTransaction {

    @FXML
    private GridPane transactionBooksCardsGridLayout;

    private ArrayList<Book> booksInTransaction;
    private ArrayList<Integer> quantityBookTransaction;

    private ArrayList<LocalDate> returnDateBookTransaction;

    private ArrayList<Boolean> isBookReturned;



    public void setTransactionBooks(ArrayList<Book> transactionBooks , ArrayList<Integer> transactionBooksQuantity , ArrayList<LocalDate> returnDateBooks , ArrayList<Boolean> isBookReturned){
        this.booksInTransaction = transactionBooks;
        this.quantityBookTransaction = transactionBooksQuantity;
        this.returnDateBookTransaction = returnDateBooks;
        this.isBookReturned =isBookReturned ;
    }

    public void loadData(){
        int column = 0;
        int row = 1;


        try{
            int no_book = 0; //zero based
            for (Book book : booksInTransaction){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewtransactions_page/borrower_bookcardtransaction.fxml"));

                VBox bookCard = fxmlLoader.load();
                // calling the controller of the loaded fxml , we create instance "object" from it
                BorrowerBookCardTransaction bookcardcontroller = fxmlLoader.getController();
                bookcardcontroller.setBook(book);
                bookcardcontroller.setData(quantityBookTransaction.get(no_book),returnDateBookTransaction.get(no_book),isBookReturned.get(no_book));

                if (column == 3){
                    column = 0;
                    ++row;
                }

                transactionBooksCardsGridLayout.add(bookCard,column++,row);
                GridPane.setMargin(bookCard,new Insets(10));
                no_book++;
            }
        }
        catch (IOException exception){
            System.out.println(exception);

        }
    }

}
