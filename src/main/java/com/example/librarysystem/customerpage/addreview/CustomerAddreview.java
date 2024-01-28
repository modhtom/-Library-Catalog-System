package com.example.librarysystem.customerpage.addreview;

import Accounts.Customer;
import com.example.librarysystem.Starter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Book.Book;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import System.*;
import Book.*;
public class CustomerAddreview implements Initializable {

    @FXML
    private Button addReview_btn;


    @FXML
    private TableColumn<Book, String> bookAuthor_col;

    @FXML
    private TableColumn<Book, String> bookCategory_col;

    @FXML
    private TableColumn<Book, String> bookID_col;

    @FXML
    private TextArea bookID_comment_input;

    @FXML
    private TextField bookID_input;

    @FXML
    private ComboBox<Integer> bookID_rating_combobox;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> bookTitle_column;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // it works so I dont need book model class
        bookID_col.setCellValueFactory(new PropertyValueFactory<>("Book_ID")); // must be the name of id attribute in Book class
        bookTitle_column.setCellValueFactory(new PropertyValueFactory<>("Book_Title"));
        bookCategory_col.setCellValueFactory(new PropertyValueFactory<>("category"));
        bookAuthor_col.setCellValueFactory(new PropertyValueFactory<>("Author"));
        bookTable.setItems(getBooks());
        bookID_rating_combobox.getItems().addAll(1,2,3,4,5);
        bookID_rating_combobox.setValue(3);

        /*
        // that works well if i have book model class , but i will try it using original class
        bookID_col.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookTitle_column.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookCategory_col.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        bookAuthor_col.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        bookTable.setItems(getBooks());
        bookID_rating_combobox.getItems().addAll(1,2,3,4,5);
        bookID_rating_combobox.setValue(3);*/




        /*for (Book book : Library.books){
            // you can call the constructor then add to the array list if you want to make that
            bookmodel.setBookID(book.getBook_ID());
            bookmodel.setBookName(book.getBook_Title());
            bookmodel.setBookCategory(book.getCategory());
            bookmodel.setAuthorName(book.getAuthor());


        }*/

        /*for (Book book : Library.books){
            bookID_col.setCellValueFactory(new PropertyValueFactory<Book,String>(book.getBook_ID()));
            bookTitle_column.setCellValueFactory(new PropertyValueFactory<Book,String>(book.getBook_Title()));
            bookCategory_col.setCellValueFactory(new PropertyValueFactory<Book,String>(book.getCategory()));
            bookAuthor_col.setCellValueFactory(new PropertyValueFactory<Book,String>(book.getAuthor()));
        }*/

    }

    /*public ArrayList<BookModel> getBooks(){
        ArrayList<BookModel> books = new ArrayList<BookModel>();
        for (Book book :Library.books){
            books.add(new BookModel(book.getBook_ID(),book.getBook_Title(),book.getCategory(),book.getAuthor()));
        }
        return books;
    }*/


    // Array List doesnt work in table , they made ObservableList cuz it has many uses in ui
    /*public ObservableList<BookModel> getBooks(){
        ObservableList<BookModel> books = FXCollections.observableArrayList();
        for (Book book :Library.books){
            books.add(new BookModel(book.getBook_ID(),book.getBook_Title(),book.getCategory(),book.getAuthor()));
        }
        return books;

    }*/

    public ObservableList<Book> getBooks(){
        ObservableList<Book> books = FXCollections.observableArrayList(Library.books);
        return books;

    }



    public void onSubmit(ActionEvent event){
        // 3 checks
        // 1- book id must be correct
        // 2- User must bought that product in order history
        // 3- user must not have write review for that product before
        Customer customer_logged = Library.logged_in_customer();
        String bookID = bookID_input.getText();
        String customer_comment = bookID_comment_input.getText();
        int customer_Rating = bookID_rating_combobox.getValue();


        boolean book_found = false;
        boolean book_found_in_order = false;

        if (bookID.isBlank() || bookID.isEmpty() ||
            customer_comment.isEmpty() || customer_comment.isBlank()){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Not valid data",null);
            return;
        }

        for (Book book : Library.books){
            if(book.getBook_ID().equals(bookID)){ // book id is valid
                book_found = true;

                for (String book_id : customer_logged.allBooksIdsCustomerBought()){ // that customer bought that book , we find it in his orders
                    if(book_id.equals(bookID)){
                        book_found_in_order = true;
                    }

                }

                for (String customerIDs : book.getCustomerIdsReviewBook()){ // that customer reviewed that book before
                    if(customerIDs.equals(customer_logged.getId())){
                        Starter.showAlert(Alert.AlertType.ERROR,"Error","You Reviewed that book before","Sorry You only review the book once");
                        return;

                    }
                }

                if (!book_found_in_order){
                    Starter.showAlert(Alert.AlertType.ERROR,"Error","You did not buy this product to review it","We didn't find that book in your orders, You must buy it first to be able to review it");
                    return;
                }
                Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulaitons","We added Your review",null);
                book.reviews.add(new Review(customer_logged.getId(),customer_comment,customer_Rating));
                return;
            }
        }
        if (!book_found){
            Starter.showAlert(Alert.AlertType.ERROR,"Error","This book Id is invalid","We dont have a book with that id");
            return;
        }


    }



}



