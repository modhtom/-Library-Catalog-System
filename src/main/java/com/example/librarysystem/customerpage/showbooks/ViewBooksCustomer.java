package com.example.librarysystem.customerpage.showbooks;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import System.*;
import Book.*;

public class ViewBooksCustomer implements Initializable {

    @FXML
    private GridPane booksCardsGridLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //int column = 0 , row = 0;
        // Set equal column constraints for the GridPane
        /*for (int i = 0; i < 3; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100 / 3.0); // Equal width for each of the 3 columns
            booksCardsGridLayout.getColumnConstraints().add(columnConstraints);
        }*/
        int column = 0;
        int row = 1;
        try{
            int noofBooks = 1;
            for (Book book : Library.books){
                System.out.println("No Of Book: " + noofBooks);
                System.out.println("row: " + row + "\n" + "column: " + column);
                noofBooks++;


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/viewbooks_page/bookcard_buy.fxml"));

                //Pane root = fxmlLoader.load();
                VBox root = fxmlLoader.load();
                // calling the controller of the loaded fxml , we create instance "object" from it
                BookcardBuy bookCard = fxmlLoader.getController();
                bookCard.setBook(book);
                bookCard.setData();
                //root.setPrefSize(200,368);
                //root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                /*if (column == 3){
                    column = 0;
                    row++;

                }*/
                if (column == 5){
                    column = 0;
                    ++row;
                }

                booksCardsGridLayout.add(root,column++,row);
                GridPane.setMargin(root,new Insets(10));
                //column++;


                /*//set grid width
                booksCardsGridLayout.setMinWidth(Region.USE_COMPUTED_SIZE);
                booksCardsGridLayout.setPrefWidth(Region.USE_COMPUTED_SIZE);
                booksCardsGridLayout.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                booksCardsGridLayout.setMinHeight(Region.USE_COMPUTED_SIZE);
                booksCardsGridLayout.setPrefHeight(Region.USE_COMPUTED_SIZE);
                booksCardsGridLayout.setMaxHeight(Region.USE_PREF_SIZE);

                booksCardsGridLayout.setMargin(root, new Insets(10));*/

            }
        }
        catch (IOException exception){
            System.out.println(exception);

        }
    }
}
