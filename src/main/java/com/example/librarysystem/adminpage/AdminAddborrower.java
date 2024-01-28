package com.example.librarysystem.adminpage;

import com.example.librarysystem.Starter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import System.*;

public class AdminAddborrower implements Initializable {

    @FXML
    private Button addborrower_btn;

    @FXML
    private Label admin_name;

    @FXML
    private Button back_btn;

    @FXML
    private TextField email_input;

    @FXML
    private Button logout_btn;

    @FXML
    private TextField name_input;

    @FXML
    private PasswordField password_input;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin_name.setText(Library.logged_in_admin().getName());
    }


    public void onAddBorrower(ActionEvent event){
        String name = name_input.getText();
        String email = email_input.getText();
        String passwrod = password_input.getText();

        if (name.isBlank() || name.isEmpty() ||
            email.isEmpty() || email.isBlank()||
            passwrod.isBlank() || passwrod.isEmpty()){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data");
            return;
        }

        if (Library.registerBorrower(name , email , passwrod)){
            Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","Valid data","You create new Borrower account successfully");
            //Starter.switchScreen();

        }else {
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data");
        }



    }


    public void onLogOut(ActionEvent event) throws IOException {
        Library.logOutAdmin();
        Starter.switchScreen("/com/example/librarysystem/starter_pages/home_page.fxml","Home Page");
    }

    public void onBack(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_homepage.fxml","Admin Home");

    }

}
