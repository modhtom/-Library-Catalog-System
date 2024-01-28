package com.example.librarysystem.adminpage;

import Accounts.Borrower;
import Book.Book;
import com.example.librarysystem.Starter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Accounts.Admin;
import System.Library;
import Book.Book;
public class AdminRemoveBook implements Initializable {

    @FXML
    private Label admin_name;

    @FXML
    private Button back_btn;

    @FXML
    private TableColumn<Book, String> bookAuthor_col;

    @FXML
    private TableColumn<Book ,String> bookCategory_col;

    @FXML
    private TableColumn<Book,String> bookID_col;

    @FXML
    private TextField bookID_delete_field;

    @FXML
    private TableColumn<Book,String> bookTitle_column;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private Button delete_btn;

    @FXML
    private Button logout_btn;

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_homepage.fxml","Admin Home");

    }

    @FXML
    void onDelete(ActionEvent event) {
        String book_id = bookID_delete_field.getText();
        ObservableList<Book> booksInTable = bookTable.getItems();

        boolean bookInLibrary = false;
        if (book_id.isEmpty() || book_id.isBlank()){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data",null);
            return;
        }
        for (Book book : Library.books){
            if(book.getBook_ID().equals(book_id)){
                bookInLibrary = true;
                Library.books.remove(book);
                booksInTable.remove(book);
                break;
            }
        }

        if (!bookInLibrary){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","This book Id is invalid","We don't have a book with that id");
            return;
        }

    }

    @FXML
    void onLogOut(ActionEvent event) throws IOException {
        Library.logOutAdmin();
        Starter.switchScreen("/com/example/librarysystem/starter_pages/home_page.fxml","Home Page");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookID_col.setCellValueFactory(new PropertyValueFactory<>("Book_ID")); // must be the name of id attribute in Book class
        bookTitle_column.setCellValueFactory(new PropertyValueFactory<>("Book_Title"));
        bookCategory_col.setCellValueFactory(new PropertyValueFactory<>("category"));
        bookAuthor_col.setCellValueFactory(new PropertyValueFactory<>("Author"));
        bookTable.setItems(getBooks());
        admin_name.setText(Library.logged_in_admin().getName());
    }

    public ObservableList<Book> getBooks(){
        ObservableList<Book> books = FXCollections.observableArrayList(Library.books);
        return books;

    }
}
