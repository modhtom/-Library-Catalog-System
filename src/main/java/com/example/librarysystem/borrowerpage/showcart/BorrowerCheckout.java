package com.example.librarysystem.borrowerpage.showcart;

import Accounts.Borrower;
import Orders.BorrowerCart;
import com.example.librarysystem.Starter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import System.Library;
public class BorrowerCheckout implements Initializable {

    @FXML
    private TextField Address_field;

    @FXML
    private TextField CVV_field;

    @FXML
    private TextField cardNumber_field;

    @FXML
    private TextField city_field;

    @FXML
    private Label discount_label;

    @FXML
    private DatePicker expirationdate_field;

    @FXML
    private VBox payVisa_VBOX;

    @FXML
    private Button pay_btn;

    @FXML
    private ComboBox<String> payment_combobox;

    @FXML
    private TextField phoneNumber_field;

    @FXML
    private Label shipping_label;

    @FXML
    private Label subtotalPrice_label;

    @FXML
    private Label totalPrice_label;

    private BorrowerCart cart;
    private Borrower borrower = Library.logged_in_borrower();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cart = borrower.getShoppingCart();
        payment_combobox.getItems().addAll("Cash On Delivery" , "Visa | Mastercard");
        payVisa_VBOX.setVisible(false);
        payment_combobox.setValue("Cash On Delivery");
        setData(); // cuz we already have the data not taking it from another controller
    }

    // we donot take data from another another controller fa 3shan kda mfesh setCart(BorrowerCart cart);

    public void setData(){
        subtotalPrice_label.setText("$" + cart.calculatepriceBeforeDiscount());
        shipping_label.setText("$" + cart.getDeliveryServiceFees());
        discount_label.setText("$" + cart.calculatepriceBeforeDiscount() * cart.applyDiscount());
        totalPrice_label.setText("$" + cart.calculateTotalPrice());

    }

    public void handleComboBoxSelectionPaymentMethod() {
        if (payment_combobox.getValue().equals("Visa | Mastercard")){
            payVisa_VBOX.setVisible(true);
        }
        else{
            payVisa_VBOX.setVisible(false);
        }
    }

    @FXML
    void onPay(ActionEvent event) {
        String phonenumber = phoneNumber_field.getText();
        String city = city_field.getText();
        String address = Address_field.getText();

        if (Starter.isEmptyOrBlank(phonenumber) ||
            Starter.isEmptyOrBlank(city)||
            Starter.isEmptyOrBlank(address) ||
            (!Starter.isOnlyNumeric(phonenumber)) ||
            phonenumber.length()!= 11)
        {
            Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data to complete the order");
            return;
        }


        if (payment_combobox.getValue().equals("Visa | Mastercard")){
            String cardnum = cardNumber_field.getText();
            String cardcvv = CVV_field.getText();
            LocalDate expired = expirationdate_field.getValue();

            if (Starter.isEmptyOrBlank(cardnum) ||
                Starter.isEmptyOrBlank(cardcvv) ||
                expired == null){
                Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data to complete the order");
                return;
            }

            if (expired.getYear() < 2023 || (expired.getYear() == 2023 && expired.getMonthValue() < 12)){
                Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data to complete the order");
                return;
            }

            if (cardnum.length() != 16 || cardcvv.length() != 3 || (!Starter.isOnlyNumeric(cardnum)) || (!Starter.isOnlyNumeric(cardcvv))){
                Starter.showAlert(Alert.AlertType.ERROR,"Error","Invalid data","Please Enter valid data to complete the order");
                return;
            }
        }

        try{
            borrower.shoppingCart.completePurchase();
            Starter.showAlert(Alert.AlertType.INFORMATION,"Congratulations","I hope these books find you well","I hope you like our books");
            Starter.switchScreen("/com/example/librarysystem/borrower_pages/borrower_homepage.fxml","Home Page");
        }
        catch (Exception exception){
            System.out.println("There is error");
        }

    }



}
