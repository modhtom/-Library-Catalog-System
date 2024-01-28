package com.example.librarysystem.customerpage.showcart;

import Book.Book;
import com.example.librarysystem.Starter;
import com.example.librarysystem.customerpage.CustomerHomepage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Accounts.Customer;
import System.Library;

public class ViewCartCustomer implements Initializable {
    // private Customer customer = Library.logged_in_customer();

    @FXML
    private GridPane booksCardsCartGridLayout;

    @FXML
    private Button checkOut_btn;


    // hna ehna bn3dl 3la fxml tanya b controller bta3ha w bndef panel bta3tha fy scene aw page ely 3ndna
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customer customer = Library.logged_in_customer();
        int column = 0;
        int row = 1;
        try{

            if (customer.shoppingCart.getNoOfBooksInCart() == 0){
                checkOut_btn.setVisible(false);
                Label nobookinCart = new Label("No book in the cart");
                booksCardsCartGridLayout.add(nobookinCart,1,1);
            }

            for (Book book : customer.shoppingCart.getAllBooksInCart()){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_pages/viewcart_page/bookcard_cartbuy.fxml"));

                VBox bookCard = fxmlLoader.load();
                // calling the controller of the loaded fxml , we create instance "object" from it
                BookCardCart bookcardcontroller = fxmlLoader.getController();
                bookcardcontroller.setBook(book);
                bookcardcontroller.setData();

                if (column == 3){
                    column = 0;
                    ++row;
                }

                booksCardsCartGridLayout.add(bookCard,column++,row);
                GridPane.setMargin(bookCard,new Insets(10));
            }
        }
        catch (IOException exception){
            System.out.println(exception);

        }

    }

    // hna ehna hn3dl data fy fxml tanya b controller bta3ha w lakn b3dha hn3ml switch screen
    public void onCheckOut(ActionEvent event) throws IOException {
        Pane cartPage= FXMLLoader.load(getClass().getResource("/com/example/librarysystem/customer_pages/viewcart_page/customer_checkout.fxml"));
        CustomerHomepage.changeContentStackPane(cartPage);


        //Starter.switchScreen("/com/example/librarysystem/customer_pages/viewcart_page/customer_checkout.fxml","CheckOut");
        //FXMLLoader fxmlLoader = new FXMLLoader();
        //fxmlLoader.setLocation(getClass().getResource("/com/example/librarysystem/customer_checkout.fxml"));
        //Pane checkout = fxmlLoader.load(); // mmkn nkhleha parent -- mmkn t3ml handling by try and catch aw mmkn t3ml throw l exception in method signature
        //CustomerCheckout checkoutController = fxmlLoader.getController();
        //checkoutController.setCart(Library.logged_in_customer().shoppingCart);
        //checkoutController.setData();
        //Starter.getPrimaryStage().setScene(new Scene(checkout,1280,720));// we change scene"page" from one to another but in the same stage "same Window"

    }
}
