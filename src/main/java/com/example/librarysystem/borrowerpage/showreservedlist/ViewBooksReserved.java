package com.example.librarysystem.borrowerpage.showreservedlist;

import Accounts.Borrower;
import Book.Book;
import com.example.librarysystem.borrowerpage.showbooks.BookcardBorrow;
import com.example.librarysystem.borrowerpage.showbooks.BookcardReserve;
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
public class ViewBooksReserved implements Initializable {

    @FXML
    private GridPane booksCardsGridLayout;

    private Borrower borrower = Library.logged_in_borrower();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;
        try{

            for (Book book : borrower.getReservedBooks()){
                FXMLLoader fxmlLoader = new FXMLLoader();
                VBox bookCard; // to be able to use it outside if condition , so I declare it before it

                fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewreservelist_pages/bookcard_reserved.fxml"));
                bookCard = fxmlLoader.load();
                BookCardReserved bookCardController = fxmlLoader.getController();
                bookCardController.setBook(book);
                bookCardController.setData();

                if (column == 4){
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
