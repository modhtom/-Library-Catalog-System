package com.example.librarysystem.borrowerpage.showbooks;

import Book.Book;
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
import Accounts.Borrower;
import Orders.BorrowerCart;
import Orders.Transaction;

public class ViewBooksBorrower implements Initializable {

    @FXML
    private GridPane booksCardsGridLayout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int column = 0;
        int row = 1;
        try{

            for (Book book : Library.books){
                FXMLLoader fxmlLoader = new FXMLLoader();
                VBox bookCard; // to be able to use it outside if condition , so I declare it before it

                if(book.getStatus()){
                    fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewbooks_page/bookcard_borrow.fxml"));
                    bookCard = fxmlLoader.load();
                    BookcardBorrow bookCardController = fxmlLoader.getController();
                    bookCardController.setBook(book);
                    bookCardController.setData();
                }else{
                    fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewbooks_page/bookcard_reserve.fxml"));
                    bookCard = fxmlLoader.load();
                    BookcardReserve bookCardController = fxmlLoader.getController();
                    bookCardController.setBook(book);
                    bookCardController.setData();
                }

                if (column == 5){
                    column = 0;
                    ++row;
                }

                booksCardsGridLayout.add(bookCard,column++,row);
                GridPane.setMargin(bookCard,new Insets(10));
            }
        }
        catch (IOException exception){
            System.out.println(exception);

        }
    }

}
