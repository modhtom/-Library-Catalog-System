package com.example.librarysystem.adminpage;

import com.example.librarysystem.Starter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import System.*;

public class AdminHomepage implements Initializable {

    @FXML
    private Button addBook_btn;

    @FXML
    private Button addborrower_btn;

    @FXML
    private Label admin_name;

    @FXML
    private Button logout_btn;

    @FXML
    private Button removebook_btn;

    @FXML
    private Button removeborrower_btn;

    @FXML
    private Button updatebook_btn;

    @FXML
    private Button updateborrower_btn;

    @FXML
    private Button returnBook_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin_name.setText(Library.logged_in_admin().getName());
    }

    public void onLogOut(ActionEvent event) throws IOException {
        Library.logOutAdmin();
        Starter.switchScreen("/com/example/librarysystem/starter_pages/home_page.fxml","Home Page");
    }

    public void onAddBook(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_addbook.fxml","Add book");
    }

    public void onUpdateBook(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_updatebook.fxml","Update book");
    }

    public void onDeleteBook (ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_removebook.fxml","Remove book");
    }

    public void onAddBorrower(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_addborrower.fxml","Add Borrower");
    }

    public void onUpdateBorrower (ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_updateborrower.fxml","Update Borrower");
    }

    public void onDeleteBorrower (ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_removeborrower.fxml","Remove Borrower");
    }

    public void onReturnBook(ActionEvent event) throws IOException{
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_ReturnBooks.fxml","Return Book");

    }
}
