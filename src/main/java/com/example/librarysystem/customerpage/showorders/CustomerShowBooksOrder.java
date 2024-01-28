package com.example.librarysystem.customerpage.showorders;

import Book.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.layout.VBox;

public class CustomerShowBooksOrder implements Initializable {

    @FXML
    private GridPane orderBooksCardsGridLayout;
    //private ArrayList<Book> booksInOrder = new ArrayList<Book>(); // lma 3mltha initilization kda error ethl 3shan kan bydeny null pointer excpection msh 3arf leh
    //private ArrayList<Integer> quantityCustomerBookOrder = new ArrayList<Integer>();// lma 3mltha initilization kda error ethl 3shan kan bydeny null pointer excpection msh 3arf leh
    private ArrayList<Book> booksInOrder;
    private ArrayList<Integer> quantityCustomerBookOrder;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        int column = 0;
        int row = 1;
        // for tesing

        for (Book book: booksInOrder){
            System.out.println(book);
        }

        try{
            int no_book = 0; //zero based
            for (Book book : booksInOrder){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/vieworders_page/customer_bookcardorder.fxml"));

                VBox bookCard = fxmlLoader.load();
                // calling the controller of the loaded fxml , we create instance "object" from it
                CustomerBookCardOrder bookcardcontroller = fxmlLoader.getController();
                bookcardcontroller.setBook(book);
                bookcardcontroller.setData(quantityCustomerBookOrder.get(no_book));

                if (column == 3){
                    column = 0;
                    ++row;
                }

                orderBooksCardsGridLayout.add(bookCard,column++,row);
                GridPane.setMargin(bookCard,new Insets(10));
                no_book++;
            }
        }
        catch (IOException exception){
            System.out.println(exception);

        }*/

    }


    public void setOrderBooks(ArrayList<Book> orderBooks , ArrayList<Integer> orderBooksCustomerQuantity){
        this.booksInOrder = orderBooks;
        this.quantityCustomerBookOrder = orderBooksCustomerQuantity;
    }

    public void loadData(){
        int column = 0;
        int row = 1;
        // for tesing

        for (Book book: booksInOrder){
            System.out.println(book);
        }

        try{
            int no_book = 0; //zero based
            for (Book book : booksInOrder){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/vieworders_page/customer_bookcardorder.fxml"));

                VBox bookCard = fxmlLoader.load();
                // calling the controller of the loaded fxml , we create instance "object" from it
                CustomerBookCardOrder bookcardcontroller = fxmlLoader.getController();
                bookcardcontroller.setBook(book);
                bookcardcontroller.setData(quantityCustomerBookOrder.get(no_book));

                if (column == 3){
                    column = 0;
                    ++row;
                }

                orderBooksCardsGridLayout.add(bookCard,column++,row);
                GridPane.setMargin(bookCard,new Insets(10));
                no_book++;
            }
        }
        catch (IOException exception){
            System.out.println(exception);

        }
    }
}
