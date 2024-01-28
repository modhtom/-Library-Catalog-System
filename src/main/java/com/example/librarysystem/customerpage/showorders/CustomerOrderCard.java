package com.example.librarysystem.customerpage.showorders;

import com.example.librarysystem.Starter;
import com.example.librarysystem.customerpage.CustomerHomepage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Orders.Order;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import Book.Book;

public class CustomerOrderCard {

    @FXML
    private Label orderDate_label;

    @FXML
    private Label orderID_label;

    @FXML
    private Label orderNumber_Label;

    @FXML
    private Label totalPrice_label;

    @FXML
    private Button viewBooksOfOrder_btn;

    private Order order;


    public void setOrder(Order order){
        this.order = order;
    }

    public void setData(int no_ofOrder){
        orderNumber_Label.setText("Order #" + no_ofOrder);
        orderID_label.setText("Order ID:" + order.getOrderId());
        totalPrice_label.setText("Total Price: $" + order.getTotalPrice());
        orderDate_label.setText(String.valueOf(order.getOrderDate())); // casting local date to string

    }

    public void viewBookOrder(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/vieworders_page/customer_showbooksorder.fxml")); // main node

        System.out.println("FXML Loader: " + fxmlLoader);
        //Parent booksOrder = fxmlLoader.load();
        Pane booksOrder = fxmlLoader.load(); // main node of the fxml - main layout main panel
        CustomerShowBooksOrder booksOrderController = fxmlLoader.getController();
        System.out.println("TESTTTT");
        // for testing
        for (Book book : order.getAllBooks()){
            System.out.println(book);
        } // that works well and it printed the name of books but i dont know why the data didnt send to the another controller
        booksOrderController.setOrderBooks(order.getAllBooks(),order.getQuantity());
        booksOrderController.loadData();
        CustomerHomepage.changeContentStackPane(booksOrder);

        /*
        It works well
        CustomerHomepage.getStackPane().getChildren().removeAll();
        CustomerHomepage.getStackPane().getChildren().addAll(booksOrder);
        */

        /*
        doesnt work cuz it doesnt change the screen but doesnt give me error
        FXMLLoader loader1  = new FXMLLoader();
        loader1.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/customer_homepage.fxml"));
        Pane customerHomePage = loader1.load();
        CustomerHomepage homePageController = loader1.getController();

        homePageController.changeContentStackPane(booksOrder);*/

        //Starter.getPrimaryStage().setScene(new Scene(booksOrder,1280,720)); // that works if you want it in new scene new screen



    }




}
