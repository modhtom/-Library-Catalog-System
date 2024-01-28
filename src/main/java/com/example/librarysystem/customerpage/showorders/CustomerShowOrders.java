package com.example.librarysystem.customerpage.showorders;

import Accounts.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import System.Library;
import Orders.Order;

public class CustomerShowOrders implements Initializable {

    @FXML
    private GridPane orderCardsGridLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customer customer = Library.logged_in_customer();
        int column = 0;
        int row = 1;
        try{
            int order_no = 1;
            for (Order order : customer.getOrderHistory()){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/vieworders_page/customer_ordercard.fxml"));

                VBox orderCard = fxmlLoader.load();
                // calling the controller of the loaded fxml , we create instance "object" from it
                CustomerOrderCard orderCardController = fxmlLoader.getController();
                orderCardController.setOrder(order);
                orderCardController.setData(order_no);

                if (column == 3){
                    column = 0;
                    ++row;
                }

                orderCardsGridLayout.add(orderCard,column++,row);
                GridPane.setMargin(orderCard,new Insets(10));
                order_no++;
            }
        }
        catch (IOException exception){
            System.out.println(exception);

        }

    }
}
