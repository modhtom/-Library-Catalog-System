package com.example.librarysystem.customerpage;

import com.example.librarysystem.Starter;
import com.example.librarysystem.customerpage.showbooks.BookMoreInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import System.*;
import Book.Book;


import javafx.scene.text.Text;
import org.controlsfx.control.textfield.TextFields;

public class CustomerHomepage implements Initializable {

    @FXML
    private StackPane contentArea; // I will try it static but i dont know it will work or not

    @FXML
    private Button showBooks_btn;
    @FXML
    private Button showCart_btn;
    @FXML
    private Button showOrderHistory_btn;
    @FXML
    private Button addReview_btn;
    @FXML
    private Button logOut_btn;

    @FXML
    private Label hello_label;

    @FXML
    private TextField search_field;
    @FXML
    private ComboBox<String> searchby_combobox;
    @FXML
    private Button search_btn;

    private static StackPane staticStackPane;

    //private static StackPane contentAreacopy = this.contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // home page after when customer login
        staticStackPane = contentArea;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/customer_pages/viewbooks_page/viewbooks_customer.fxml")); // main node
            //contentArea.getChildren().removeAll();
            //contentArea.getChildren().addAll(root);
            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);
        }
        catch (IOException exception){
            System.out.println("There is error");

        }
        hello_label.setText("Hello, " + Library.logged_in_customer().getName());

        searchby_combobox.getItems().addAll("Book Name","Author Name");
        searchby_combobox.setValue("Book Name");








    }

    public void handleComboBoxSelectionSearch() {
        // Auto complete Search
        if(searchby_combobox.getValue().equals("Book Name")){
            TextFields.bindAutoCompletion(search_field,Library.getAllBooksNames());
        }else{
            TextFields.bindAutoCompletion(search_field,Library.getAllAuthorsNames());
        }
    }

    public void onSearch(ActionEvent event) throws IOException {
        String search_selection = searchby_combobox.getValue();
        String search_value = search_field.getText();
        Book book_search = null;
        boolean book_found = false;
        if(search_selection.equals("Book Name")){
            for(Book book: Library.books){
                if(book.getBook_Title().equals(search_value)){
                    book_search = book;
                    book_found = true;
                }
            }
        }else{ //Author Name
            for (Book book : Library.books){
                if(book.getAuthor().equals(search_value)){
                    // switch screen to more info of that book
                    book_search = book;
                    book_found = true;
                }
            }
        }

        if(book_found){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/viewbooks_page/book_moreinfo.fxml"));
            Pane root = fxmlLoader.load();
            BookMoreInfo bookMoreInfo = fxmlLoader.getController();
            bookMoreInfo.setBook(book_search);
            bookMoreInfo.setData();
            bookMoreInfo.showComment();
            CustomerHomepage.changeContentStackPane(root);
        }else{
            Label bookNotFound = new Label("Sorry We don't have that book");
            contentArea.getChildren().clear();
            contentArea.getChildren().add(bookNotFound);
        }

    }

    public void onShowBooks(ActionEvent event) throws IOException {
        // we will load the node in Parent object cuz IT stores any object , any type of panels
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/customer_pages/viewbooks_page/viewbooks_customer.fxml")); // main node
        //contentArea.getChildren().removeAll();
        //contentArea.getChildren().addAll(root);
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);

    }

    public void onShowCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/customer_pages/viewcart_page/viewcart_customer.fxml")); // main node
        //contentArea.getChildren().removeAll();
        //contentArea.getChildren().addAll(root);
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);

    }

    public void onShowOrdersHistory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/customer_pages/vieworders_page/customer_showordershistory.fxml")); // main node
        //contentArea.getChildren().removeAll();
        //contentArea.getChildren().addAll(root);
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);

    }

    public void onAddReview(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarysystem/customer_pages/addreview_page/customer_addreview.fxml")); // main node
        //contentArea.getChildren().removeAll();
        //contentArea.getChildren().addAll(root);
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);

    }

    public void onLogout(ActionEvent event) throws IOException {
        Library.logOutCustomer();
        Starter.switchScreen("/com/example/librarysystem/starter_pages/home_page.fxml","Home page");

    }


    public static void changeContentStackPane(Pane mainpane){
        //staticStackPane.getChildren().removeAll();
        //staticStackPane.getChildren().addAll(mainpane);
        staticStackPane.getChildren().clear();
        staticStackPane.getChildren().add(mainpane);
    }

    public static void changeContentStackPane(Parent mainpane){
        //staticStackPane.getChildren().removeAll();
        //staticStackPane.getChildren().addAll(mainpane);
        staticStackPane.getChildren().clear();
        staticStackPane.getChildren().add(mainpane);
    }

    public static StackPane getStackPane(){
        return staticStackPane;
    }



}
