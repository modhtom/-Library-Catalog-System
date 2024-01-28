package Orders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import System.*;
import Orders.*;
import Accounts.*;
import Book.*;
import Accounts.Customer;
import com.example.librarysystem.Starter;
import javafx.scene.control.Alert;

public class CustomerCart extends ShoppingCart{

    public void addToCart(Book book){
        CartItem item = findItemInCart(book);
        if (item != null) { // if book found in shopping cart just add quantity
            Starter.showAlert(Alert.AlertType.ERROR,"Error","You It's already in the cart",null); // dh kan bdl ma a3mlha boolean w a2ol lw false a3ml alert fa 3mltha hna w khlas
            return;
        } else {
            Book tempBook = book;
            int quantity = 1;
            CartItem cartitem = new CartItem (book,quantity);
            Library.updateBook(cartitem.getBook(),Library.BookIndex(tempBook));
            cartItems.add(cartitem);// if book not found in shopping cart



        }
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        double discount = applyDiscount();

        for (CartItem item : cartItems) {
            double itemPrice = item.getBook().getPriceBuying() * item.getQuantity();
            double discountedItemPrice = itemPrice - (itemPrice * discount);
            totalPrice += discountedItemPrice;
        }
        return totalPrice + deliveryServicePrice;
    }


    public double calculatepriceBeforeDiscount() {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getBook().getPriceBuying() * item.getQuantity();
        }
        return totalPrice;
    }


    public void completePurchase(){
        Customer customerLoggedIn = Library.logged_in_customer();
        ArrayList<Book> CartBooks=getAllBooksInCart();
        ArrayList<Integer> quantities = getAllQuantitiesOfBooksInCart();

        Order order = new Order(getAllBooksInCart(),getAllQuantitiesOfBooksInCart(), LocalDate.now());
        if (customerLoggedIn != null){
            for(int i=0;i<CartBooks.size();i++){
                CartBooks.get(i).setStock(CartBooks.get(i).getStock()-quantities.get(i));
                if(CartBooks.get(i).getStock()<=0)
                    CartBooks.get(i).setStatus(false);
            }
            customerLoggedIn.ordersHistory.add(order);
            clearCart();
        }

    }
}
