package com.example.librarysystem;

import System.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginPage implements Initializable {

    @FXML
    private TextField email_input;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_input;

    @FXML
    private Button register_btn;

    @FXML
    private ChoiceBox<String> typeaccount_box;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        typeaccount_box.getItems().addAll("Admin","Customer","Borrower");
        typeaccount_box.setValue("Customer");



    }

    public void onLoginBtn(ActionEvent event) throws IOException {
        String email = email_input.getText();
        String passwrod = password_input.getText();
        if (typeaccount_box.getValue().equals("Admin")){
            if (Library.loginAdmin(email,passwrod)){

                Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","Valid Authentication","Redirecting to admin page");
                Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_homepage.fxml","Admin");
                return; // so what I will write after if that makes condition false like else
            }
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid Username or password","Please Enter valid Email and Passwrod to authenticate");

        }
        else if (typeaccount_box.getValue().equals("Customer")){
            if (Library.loginCustomer(email,passwrod)){

                Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","Valid Authentication","Redirecting to Customer page");
                Starter.switchScreen("/com/example/librarysystem/customer_pages/customer_homepage.fxml","Customer");
                return;

            }
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid Username or password","Please Enter valid Email and Passwrod to authenticate");

        }
        else {// typeaccount_box.getValue().equals("Borrower")
            if (Library.loginBorrower(email,passwrod)){

                Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","Valid Authentication","Redirecting to Borrower page");
                Starter.switchScreen("/com/example/librarysystem/borrower_pages/borrower_homepage.fxml","Borrower");

                String duedate_msg = Notification.DueDate();
                String pickup_msg = Notification.PickUp();
                if(!duedate_msg.isEmpty()){
                    Starter.notificationBody("Due Date",duedate_msg,4);
                }
                if(!pickup_msg.isEmpty()){
                    Starter.notificationBody("Pick Up",pickup_msg,4);
                }
                return;
            }
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid Username or password","Please Enter valid Email and Passwrod to authenticate");
        }


    }

    public void onRegister (ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/starter_pages/register_page.fxml","Register");
    }

}

