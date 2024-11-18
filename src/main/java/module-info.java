module com.example.week8ecommerce {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.week8ecommerce to javafx.fxml;
    exports com.example.week8ecommerce;
}