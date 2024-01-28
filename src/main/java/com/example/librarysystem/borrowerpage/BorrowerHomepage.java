package com.example.librarysystem.borrowerpage;

import Book.Book;
import com.example.librarysystem.Starter;
import com.example.librarysystem.borrowerpage.showbooks.BookMoreInfo;
import com.example.librarysystem.customerpage.CustomerHomepage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import System.Library;
import Accounts.Borrower;
import Orders.BorrowerCart;
import Orders.Transaction;
import org.controlsfx.control.textfield.TextFields;


public class BorrowerHomepage implements Initializable {

    @FXML
    private StackPane contentArea;

    @FXML
    private Label helloBorrower_label;

    @FXML
    private Button logOut_btn;

    @FXML
    private Button search_btn;

    @FXML
    private TextField search_field;

    @FXML
    private ComboBox<String> searchby_combobox;

    @FXML
    private Button showBooks_btn;

    @FXML
    private Button showCart_btn;

    @FXML
    private Button showOrderHistory_btn;

    @FXML
    private Button reservedList_btn;

    private static StackPane staticStackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staticStackPane = contentArea;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/borrower_pages/viewbooks_page/viewbooks_borrower.fxml")); // main node
            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);
        }
        catch (IOException exception){
            System.out.println("There is error");

        }
        helloBorrower_label.setText("Hello, " + Library.logged_in_borrower().getName());

        searchby_combobox.getItems().addAll("Book Name","Author Name");
        searchby_combobox.setValue("Book Name");
    }

    public static void changeContentStackPane(Pane mainpane){
        staticStackPane.getChildren().clear();
        staticStackPane.getChildren().add(mainpane);
    }

    public static void changeContentStackPane(Parent mainpane){
        staticStackPane.getChildren().clear();
        staticStackPane.getChildren().add(mainpane);
    }


    @FXML
    void handleComboBoxSelectionSearch(ActionEvent event) {
        if(searchby_combobox.getValue().equals("Book Name")){
            TextFields.bindAutoCompletion(search_field,Library.getAllBooksNames());
        }else{
            TextFields.bindAutoCompletion(search_field,Library.getAllAuthorsNames());
        }

    }

    @FXML
    void onLogout(ActionEvent event) throws IOException {
        Library.logOutBorrower();
        Starter.switchScreen("/com/example/librarysystem/starter_pages/home_page.fxml","Home page");
    }

    @FXML
    void onSearch(ActionEvent event) throws IOException {
        String search_selection = searchby_combobox.getValue();
        String search_value = search_field.getText();
        Book book_search = null;
        boolean book_found = false;
        if(search_selection.equals("Book Name")){
            for(Book book: Library.books){
                if(book.getBook_Title().equals(search_value)){
                    book_search = book;
                    book_found = true;
                }
            }
        }else{ //Author Name
            for (Book book : Library.books){
                if(book.getAuthor().equals(search_value)){
                    // switch screen to more info of that book
                    book_search = book;
                    book_found = true;
                }
            }
        }

        if(book_found){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/borrower_pages/viewbooks_page/book_moreinfo.fxml"));
            Pane mainPanel = fxmlLoader.load();

            // ew3a t3ml import l class BookMoreInfo ely fy customer 3shan homa eltnen bnfs elesm yfrk package bs fa kda ytlghbt
            BookMoreInfo bookMoreInfoController = fxmlLoader.getController();
            bookMoreInfoController.setBook(book_search);
            bookMoreInfoController.setData();
            if (book_search.getStatus()) {
                bookMoreInfoController.showBorrowBtn();
            } else {
                bookMoreInfoController.showReserveBtn();
            }

            BorrowerHomepage.changeContentStackPane(mainPanel);
        }else{
            Label bookNotFound = new Label("Sorry We don't have that book");
            contentArea.getChildren().clear();
            contentArea.getChildren().add(bookNotFound);
        }

    }

    @FXML
    void onShowBooks(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/borrower_pages/viewbooks_page/viewbooks_borrower.fxml")); // main node
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);

    }

    @FXML
    void onShowCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/borrower_pages/viewcart_page/viewcart_borrower.fxml")); // main node
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);

    }

    @FXML
    void onShowTransactionsHistory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/borrower_pages/viewtransactions_page/borrower_showtransactionshistory.fxml")); // main node
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);

    }



    public static StackPane getStackPane(){
        return staticStackPane;
    }

    public void onReservedList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/borrower_pages/viewreservelist_pages/viewbooks_reservedlist.fxml")); // main node
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);
    }
}
