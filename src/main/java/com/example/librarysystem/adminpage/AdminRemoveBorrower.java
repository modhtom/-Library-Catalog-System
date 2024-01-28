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
import System.Library;
public class AdminRemoveBorrower implements Initializable {

    @FXML
    private Label admin_name;

    @FXML
    private Button back_btn;

    @FXML
    private TextField borrowerID_delete_field;

    @FXML
    private TableView<Borrower> borrowerdetails_table;

    @FXML
    private TableColumn<Borrower,String> borroweremail_column;

    @FXML
    private TableColumn<Borrower,String> borrowerid_column;

    @FXML
    private TableColumn<Borrower,String> borrowername_column;

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
        String borrower_id = borrowerID_delete_field.getText();
        ObservableList<Borrower> borrowersinTable = borrowerdetails_table.getItems();

        boolean borrowerInLibrary = false;
        if (borrower_id.isEmpty() || borrower_id.isBlank()){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data",null);
            return;
        }
        for (Borrower borrower : Library.borrowers){
            if(borrower.getId().equals(borrower_id)){
                borrowerInLibrary = true;
                Library.borrowers.remove(borrower);
                borrowersinTable.remove(borrower);
            }
        }

        if (!borrowerInLibrary){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","This borrower Id is invalid","We dont have borrower with that id");
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
        borrowerid_column.setCellValueFactory(new PropertyValueFactory<>("id")); // must be the name of id attribute in Book class
        borrowername_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        borroweremail_column.setCellValueFactory(new PropertyValueFactory<>("email"));
        borrowerdetails_table.setItems(getBorrowers());
        admin_name.setText(Library.logged_in_admin().getName());
    }

    public ObservableList<Borrower> getBorrowers(){
        ObservableList<Borrower> borrowers = FXCollections.observableArrayList(Library.borrowers);
        return borrowers;

    }
}
