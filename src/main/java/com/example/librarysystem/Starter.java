package com.example.librarysystem;
import Accounts.Admin;
import Book.*;
import Orders.Order;
import System.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.function.BiPredicate;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Starter extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        //System.out.println(Starter.class.getResource("/com/example/librarysystem/starter_pages/home_page.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Starter.class.getResource("/com/example/librarysystem/starter_pages/home_page.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Starter.class.getResource("customer_addreview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();


        primaryStage.setOnCloseRequest(event -> {
            FileHandling.writeAllFiles();
        });




    }

    public static void main(String[] args) {
        //Book book = new Book("sdasd","dadasd",50.5,5,"TEST","2024","C:\\Users\\DELL\\IdeaProjects\\LibrarySystem\\src\\main\\resources\\imgs\\admin_sidebar.png");
        //book.reviews.add(new Review("#dadas","dsadasda",4.5F));
        //Book book = new Book("Dsadasd","dsadas",75,5,"dsadsa","22/10/2022","homescreen.png");
        //Library.books.add(book);

        FileHandling.readAllFiles(); // hydrb error lw files aslun doesnt exist
        /*Library.books.get(0).reviews.add(new Review("#1000","dasdas",4.4F)); // for testing
        Library.books.get(0).reviews.add(new Review("#1000","dasdas",4.4F)); // for testing
        Library.books.get(0).reviews.add(new Review("#1000","dasdas",4.4F)); // for testing
        Library.books.get(0).reviews.add(new Review("#1000","dasdas",4.4F)); // for testing
        Library.books.get(0).reviews.add(new Review("#1000","dasdas",4.4F)); // for testing



        for (Book book : Library.books){
            for (Review review: book.reviews){
                System.out.println(review);
            }
        }*/

        /*for (Order order : Library.customers.get(0).getOrderHistory()){
            for (Book book : order.getAllBooks()){
                System.out.println(book);
            }
        }*/
        launch();
    }



    //  I made theses methods cuz they are general, anyone can use them anywhere

    public static void switchScreen(String fxml,String title) throws IOException {
        //Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExampleOfTableView.fxml"));
        FXMLLoader root = new FXMLLoader(Starter.class.getResource(fxml));
        Scene scene = new Scene(root.load(), 1280, 720);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static boolean isOnlyNumeric(String str) {
        // Use regular expression to check if the string contains only digits
        return str.matches("\\d+");
    }
    // " " ""
    public static boolean isEmptyOrBlank(String str){
        if(str.isEmpty() || str.isBlank()){
            return true;
        }
        return false;
    }

    public static void notificationBody(String title, String msg,int time){
        Notifications.create()
                .title(title)
                .text(msg+"\n")
                .hideAfter(Duration.seconds(time))
                .showInformation();
    }
}