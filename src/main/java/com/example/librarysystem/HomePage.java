package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomePage {

    @FXML
    private Button login_btn;

    @FXML
    private Button register_btn;

    public void onLogin(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/starter_pages/login_page.fxml","Login");
    }

    public void onRegister(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/starter_pages/register_page.fxml","Register");
    }

}
