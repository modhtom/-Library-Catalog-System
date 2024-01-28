package com.example.librarysystem.adminpage;

import Accounts.Borrower;
import com.example.librarysystem.Starter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import System.Library;
import Accounts.Admin;
import Book.Book;

public class AdminUpdateBook implements Initializable {

    @FXML
    private Label admin_name;

    @FXML
    private Button back_btn;

    @FXML
    private TableColumn<Book, String> bookAuthor_col;

    @FXML
    private TableColumn<Book, Boolean> bookAvailable_col;

    @FXML
    private TableColumn<Book, String> bookCategory_col;

    @FXML
    private TableColumn<Book, String> bookID_col;

    @FXML
    private TextField bookID_update_field;

    @FXML
    private TableColumn<Book, Double> bookPrice_col;

    @FXML
    private TableColumn<Book, Integer> bookStock_col;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> bookTitle_column;

    @FXML
    private Button confirm_update_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private VBox update_VBOX;

    @FXML
    private Button update_btn;

    @FXML
    private ComboBox<String> updatedAvailability_combobox;

    @FXML
    private ComboBox<String> updatedCategory_combobox;

    @FXML
    private Spinner<Double> updatedPrice_spinner;

    @FXML
    private Spinner<Integer> updatedStock_spinner;

    @FXML
    private TextField updatedauthor_input;

    @FXML
    private TextField updatedname_input;

    @FXML
    void onBack(ActionEvent event) throws IOException {
        Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_homepage.fxml","Admin Home");

    }

    @FXML
    void onConfirmUpdate(ActionEvent event) {
        String updated_bookName = updatedname_input.getText();
        String updated_authorName = updatedauthor_input.getText();

        Double updatedPrice = updatedPrice_spinner.getValue();
        int updatedStock = updatedStock_spinner.getValue();

        String updatedAvailability = updatedAvailability_combobox.getValue();
        String updatedCategory = updatedCategory_combobox.getValue();

        if (Starter.isEmptyOrBlank(updated_bookName) ||
            Starter.isEmptyOrBlank(updated_authorName)){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data",null);
            return;
        }

        for (Book book : Library.books){
            if(book.getBook_ID().equals(bookID_update_field.getText())){
                book.setBook_Title(updated_bookName);
                book.setAuthor(updated_authorName);
                book.setCategory(updatedCategory);
                book.setStock(updatedStock);
                book.setPriceBuying(updatedPrice);
                if(updatedAvailability.equals("Yes")){
                    book.setStatus(true);
                }else{
                    book.setStatus(false);
                }
            }
        }

        Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","We updated the book with the new data",null);
        update_VBOX.setVisible(false);
        bookID_update_field.setDisable(false);
        bookID_update_field.clear();
        update_btn.setDisable(false);

        // ana msh mhtag a3ml clear lldata l2n kda kda homa hy3mlhm set tany
        updatedname_input.clear();
        updatedauthor_input.clear();

        try{
            Starter.switchScreen("/com/example/librarysystem/admin_pages/admin_updatebook.fxml","Update Borrower");

        }catch (Exception exception){
            exception.printStackTrace();
        }



    }

    @FXML
    void onLogOut(ActionEvent event) throws IOException {
        Library.logOutAdmin();
        Starter.switchScreen("/com/example/librarysystem/starter_pages/home_page.fxml","Home Page");
    }

    @FXML
    void onUpdate(ActionEvent event) {
        String book_id = bookID_update_field.getText();


        boolean bookInLibrary = false;
        if (Starter.isEmptyOrBlank(book_id)){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data",null);
            return;
        }

        for (Book book : Library.books){
            if(book.getBook_ID().equals(book_id)){
                bookInLibrary = true;

                update_VBOX.setVisible(true);
                bookID_update_field.setDisable(true);
                update_btn.setDisable(true);


                updatedname_input.setText(book.getBook_Title());
                updatedauthor_input.setText(book.getAuthor());

                updatedCategory_combobox.setValue(book.getCategory());
                if(book.getStatus()){
                    updatedAvailability_combobox.setValue("Yes");
                }else{
                    updatedAvailability_combobox.setValue("No");
                }

                updatedStock_spinner.getValueFactory().setValue(book.getStock());
                updatedPrice_spinner.getValueFactory().setValue(book.getPriceBuying());
            }
        }

        if (!bookInLibrary){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","This borrower Id is invalid","We dont have borrower with that id");
            return;
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //for 2 combo box - Category , Availability
        updatedCategory_combobox.getItems().addAll("Horror", "Crime", "Fantasy", "Historical" , "Science" , "Technology");
        updatedAvailability_combobox.getItems().addAll("Yes","No");

        // for 2 spinners - Stock & Price
        updatedPrice_spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(25,400));
        updatedStock_spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,200));



        // for table load data 7 columns
        bookID_col.setCellValueFactory(new PropertyValueFactory<>("Book_ID")); // must be the name of id attribute in Book class
        bookTitle_column.setCellValueFactory(new PropertyValueFactory<>("Book_Title"));
        bookCategory_col.setCellValueFactory(new PropertyValueFactory<>("category"));
        bookAuthor_col.setCellValueFactory(new PropertyValueFactory<>("Author"));
        bookStock_col.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bookAvailable_col.setCellValueFactory(new PropertyValueFactory<>("Status"));
        /* It doesnt work because
        bookPrice_col.setCellValueFactory(new PropertyValueFactory<>("price_buying"));
        just because javafx view table depends on netbeans naming conversions cuz it depends on accessors for the function to get data from
        fa 3shan tshtghl lazm akhly esm getter method bta3 price buying tkon getPrice_buying
        3shan da naming convesions of netbeans w java fx bt3tmd 3leh
        fa lma elgdwl
        the naming convention used by JavaFX for property accessors. JavaFX uses the JavaBeans naming convention to automatically identify property getter methods.

        fa 3shan kda hnstkhdm  setCellValueFactory that directly accesses the property using a lambda expression.
        you use a lambda expression to directly access the getPrice_buying method of the Book class.
         */
        bookPrice_col.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPriceBuying()).asObject());
        bookTable.setItems(getBooks());


        update_VBOX.setVisible(false);
        admin_name.setText(Library.logged_in_admin().getName());

    }

    public ObservableList<Book> getBooks(){
        ObservableList<Book> books = FXCollections.observableArrayList(Library.books);
        return books;
    }
}
