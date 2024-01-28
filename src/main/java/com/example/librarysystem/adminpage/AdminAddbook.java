package com.example.librarysystem.adminpage;

import Accounts.Admin;
import com.example.librarysystem.Starter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import System.*;
import Book.*;
import Accounts.Admin;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdminAddbook implements Initializable {

    @FXML
    private Button addborrower_btn;

    @FXML
    private Label admin_name;

    @FXML
    private TextField author_name_input;

    @FXML
    private Button back_btn;

    @FXML
    private TextField book_name_input;

    @FXML
    private ChoiceBox<String> category_choicebox;

    @FXML
    private Button logout_btn;

    @FXML
    private TextField price_input;

    @FXML
    private DatePicker publicationyear_input;

    @FXML
    private TextField stock_input;

    @FXML
    private Button uploadimg_btn;

    private String image_path;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        category_choicebox.getItems().addAll("Horror", "Crime", "Fantasy", "Historical" , "Science" , "Technology");
        category_choicebox.setValue("Science");
        admin_name.setText(Library.logged_in_admin().getName());

    }

    public void onAddBook(ActionEvent event){
        String bookName = book_name_input.getText();
        String bookAuthor = author_name_input.getText();
        String bookCategory = category_choicebox.getValue();
        LocalDate bookPublicationDate = publicationyear_input.getValue();
        //String bookPublicationYear = publicationyear_input.getValue().getYear(); // you must check that it's only 4 dharacters and that all of them are integer cuz it's year
        int bookStock = Integer.parseInt(stock_input.getText()); // casting from String to integer
        double bookPrice = Double.parseDouble(price_input.getText());

        // I will check bookPublication year by ascii code , string is char array
        //if image is not null  means that he chooses image , if he doesnt choose image , so it is initialized by null by compiler
        if (image_path == null ||
            bookName.isEmpty() || bookName.isBlank() ||
            bookAuthor.isEmpty() || bookAuthor.isBlank() ||
            bookPublicationDate == null || bookPublicationDate.getYear() > 2023 ||
            bookStock == 0 || bookPrice == 0){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data");
            return;
        }

        /*for (int i = 0 ; i < bookPublicationYear.length() ; i++){
            if (bookPublicationYear.charAt(i) < 1 && bookPublicationYear.charAt(i) > 9){ // checking in ascii code
                Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data");
                return;
            }
        }*/


        Admin.addBook(bookName,bookAuthor,bookCategory,String.valueOf(bookPublicationDate.getYear()),bookStock,bookPrice,image_path);
        Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","Valid data","You added new Book in Library successfully");





    }

    public void onChooseImage(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg") // limit the extensions that the user will upload
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage()); // open new window cuz stage means window so user will upload the image
        if (selectedFile != null) {
            image_path = selectedFile.toURI().toString();
        }else{
            image_path = null;
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
