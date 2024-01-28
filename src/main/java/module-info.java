module com.example.librarysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;




    opens Book; // I added this line - this will open the packahe that holds book class


    opens Accounts;
    exports Accounts; // I added this line cuz that will give java.base access to Accounts package so it will access Borrower class so Admin can see the data of borrowers


    opens com.example.librarysystem.borrowerpage; //i added this line



    opens com.example.librarysystem to javafx.fxml;
    exports com.example.librarysystem;

    exports com.example.librarysystem.customerpage;
    opens com.example.librarysystem.customerpage to javafx.fxml;

    exports com.example.librarysystem.adminpage;
    opens com.example.librarysystem.adminpage to javafx.fxml;

    exports com.example.librarysystem.customerpage.showbooks;
    opens com.example.librarysystem.customerpage.showbooks to javafx.fxml;

    exports com.example.librarysystem.borrowerpage; // i added the lines to give them access to these packages 3shan mygbsh Illegal exception
    opens com.example.librarysystem.borrowerpage.showbooks to javafx.fxml;
    opens com.example.librarysystem.borrowerpage.showtransactions to javafx.fxml;
    opens com.example.librarysystem.borrowerpage.showcart to javafx.fxml;
    opens com.example.librarysystem.borrowerpage.showreservedlist to javafx.fxml;

    exports com.example.librarysystem.customerpage.showcart;  // I added these new lines
    opens com.example.librarysystem.customerpage.showcart to javafx.fxml;

    exports com.example.librarysystem.customerpage.showorders;
    opens com.example.librarysystem.customerpage.showorders to javafx.fxml;
    exports com.example.librarysystem.customerpage.addreview;
    opens com.example.librarysystem.customerpage.addreview to javafx.fxml;
}

