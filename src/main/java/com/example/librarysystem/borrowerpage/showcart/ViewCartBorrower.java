package com.example.librarysystem.borrowerpage.showcart;

import Accounts.Borrower;
import Book.Book;
import com.example.librarysystem.borrowerpage.BorrowerHomepage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import System.Library;

public class ViewCartBorrower implements Initializable {

    @FXML
    private GridPane booksCardsCartGridLayout;

    @FXML
    private Button checkOut_btn;
    private Borrower borrower = Library.logged_in_borrower();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;
        try{
            /*if(borrower.shoppingCart.getAllBooksInCart() == null){
                checkOut_btn.setVisible(false);
                Label nobookinCart = new Label("No book in the cart");
                booksCardsCartGridLayout.add(nobookinCart,1,1);
            }*/

            // if there is no books in cart just hide checkout btn
            if (borrower.shoppingCart.getNoOfBooksInCart() == 0){
                checkOut_btn.setVisible(false);
                Label nobookinCart = new Label("No book in the cart");
                booksCardsCartGridLayout.add(nobookinCart,1,1);
            }

            for (Book book : borrower.shoppingCart.getAllBooksInCart()){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewcart_page/bookcard_cartborrow.fxml"));
                VBox bookCard = fxmlLoader.load();

                BorrowerBookCardCart bookcardcontroller = fxmlLoader.getController();
                bookcardcontroller.setBook(book);
                bookcardcontroller.setData();

                if (column == 3){
                    column = 0;
                    ++row;
                }

                booksCardsCartGridLayout.add(bookCard,column++,row);
                GridPane.setMargin(bookCard,new Insets(10));
            }
        }
        catch (IOException exception){
            System.out.println(exception);

        }

    }


    public void onCheckOut(ActionEvent event) throws IOException {
        Pane cartPage= FXMLLoader.load(getClass().getResource("/com/example/librarysystem/borrower_pages/viewcart_page/borrower_checkout.fxml"));
        BorrowerHomepage.changeContentStackPane(cartPage);

    }

}
