package com.example.librarysystem;

import Accounts.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import System.*;
public class RegisterPage implements Initializable {

    @FXML
    private ComboBox<String> combobox_typeaccount;

    @FXML
    private PasswordField confirmpass_input;

    @FXML
    private TextField email_input;

    @FXML
    private Button login_btn;

    @FXML
    private TextField name_input;

    @FXML
    private PasswordField password_input;

    @FXML
    private Button register_btn;

    @FXML
    private PasswordField secretkey_input;

    @FXML
    private VBox secretkey_layout;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox_typeaccount.getItems().addAll("Admin","Customer","Borrower");
        secretkey_layout.setVisible(false);
        combobox_typeaccount.setValue("Customer");


    }



    public void handleComboBoxSelectionAccountType() {
        if (combobox_typeaccount.getValue().equals("Admin")){
            secretkey_layout.setVisible(true);
        }
        else{
            secretkey_layout.setVisible(false);
        }
    }

    public void onRegister(ActionEvent event) {
        String fullname = name_input.getText();
        String email = email_input.getText();
        String password = password_input.getText();
        String confirm_password = confirmpass_input.getText();
        String type_account = combobox_typeaccount.getValue();

        if (fullname.isEmpty() || fullname.isBlank() ||
            email.isEmpty() || email.isBlank() ||
            password.isEmpty() || password.isBlank() ||
            confirm_password.isEmpty() || confirm_password.isBlank())
        {
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data");
            return;
        }

        if (!password.equals(confirm_password)){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Password doesn't match with confirm Password");
            return;
        }

        if (type_account.equals("Admin")){
            String secretKey = secretkey_input.getText();
            if (Library.registerAdmin(fullname,email,password,secretKey)){
                Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","Valid data , You become Admin in our system","Redirecting to login page");
                try{
                    Starter.switchScreen("/com/example/librarysystem/starter_pages/login_page.fxml", "Login");
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
            else{
                Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data");

            }
        }
        else if (type_account.equals("Customer")){
            if (Library.registerCustomer(fullname,email,password)){
                Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","Valid data , We created for you Customer Account in the system","Redirecting to login page");
                try{
                    Starter.switchScreen("/com/example/librarysystem/starter_pages/login_page.fxml", "Login");
                }catch (Exception exception){
                    exception.printStackTrace();
                }

            }
            else{
                Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data");
            }




        }
        else { //type_account.equals("Borrower")

            if (Library.registerBorrower(fullname,email,password)){
                Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","Valid data , We created for you Borrower Account in the system","Redirecting to login page");
                try{
                    Starter.switchScreen("/com/example/librarysystem/starter_pages/login_page.fxml", "Login");
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
            else{
                Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data");
            }


        }


    }

    public void onLogin(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/starter_pages/login_page.fxml", "Login");

    }
}

