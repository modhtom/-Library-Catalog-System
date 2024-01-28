package com.example.librarysystem.adminpage;

import Accounts.Borrower;
import com.example.librarysystem.Starter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
import System.Library;
import Accounts.Borrower;

public class AdminUpdateBorrower implements Initializable {

    @FXML
    private Label admin_name;

    @FXML
    private Button back_btn;

    @FXML
    private TextField borrowerID_update_field;

    @FXML
    private TableView<Borrower> borrowerdetails_table;

    @FXML
    private TableColumn<Borrower, String> borroweremail_column;

    @FXML
    private TableColumn<Borrower, String> borrowerid_column;

    @FXML
    private TableColumn<Borrower, String> borrowername_column;

    @FXML
    private Button confirm_update_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button update_btn;

    @FXML
    private TextField updatedemail_input;

    @FXML
    private TextField updatedname_input;

    @FXML
    private PasswordField updatedpassword_input;

    @FXML
    private VBox update_VBOX;

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_homepage.fxml","Admin Home");

    }

    public void onUpdate(ActionEvent event){
        String borrower_id = borrowerID_update_field.getText();


        boolean borrowerInLibrary = false;
        if (borrower_id.isEmpty() || borrower_id.isBlank()){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data",null);
            return;
        }

        for (Borrower borrower : Library.borrowers){
            if(borrower.getId().equals(borrower_id)){
                borrowerInLibrary = true;
                update_VBOX.setVisible(true);
                borrowerID_update_field.setDisable(true);
                update_btn.setDisable(true);

                updatedname_input.setText(borrower.getName());
                updatedemail_input.setText(borrower.getEmail());
                updatedpassword_input.setText(borrower.getPassword());
            }
        }

        if (!borrowerInLibrary){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","This borrower Id is invalid","We dont have borrower with that id");
            return;
        }


        // after he presses confirm update we will enable them
        // we can add button cancel if he doesnt want to change any thing that will hide HBOX and clear the text fields

        // print the old information of inputs in the text field and when he presses confirm button get their text

    }

    @FXML
    void onConfirmUpdate(ActionEvent event) {
        String updated_name = updatedname_input.getText();
        String updated_email = updatedemail_input.getText();
        String updated_password = updatedpassword_input.getText();
        if (Starter.isEmptyOrBlank(updated_name) ||
            Starter.isEmptyOrBlank(updated_email) ||
            Starter.isEmptyOrBlank(updated_password)
        ){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data",null);
            return;
        }

        for (Borrower borrower : Library.borrowers){
            if(borrower.getId().equals(borrowerID_update_field.getText())){
                if(borrower.getEmail().equals(updated_email)){
                    borrower.setName(updated_name);
                    borrower.setPassword(updated_password);
                }else{
                    if(!Library.checkEmailAndPassword(updated_email,updated_password)){
                        Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data",null);
                        return;
                    }
                    borrower.setName(updated_name);
                    borrower.setEmail(updated_email);
                    borrower.setPassword(updated_password);

                }

            }
        }
        // hya 3omrha ma ely fok tkon f false 3shan hoa lazm ylakeh fa mmkn a7ot ely t7t fok aw asebh 3ady
        Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","We updated the account with the new data",null);
        update_VBOX.setVisible(false);
        borrowerID_update_field.setDisable(false);
        borrowerID_update_field.clear();
        update_btn.setDisable(false);



        updatedname_input.clear();
        updatedemail_input.clear();
        updatedpassword_input.clear();
        try{
            Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_updateborrower.fxml","Update Borrower");

        }catch (Exception exception){
            exception.printStackTrace();
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

        update_VBOX.setVisible(false);
    }

    public ObservableList<Borrower> getBorrowers(){
        ObservableList<Borrower> borrowers = FXCollections.observableArrayList(Library.borrowers);
        return borrowers;

    }
}
