module com.littlecats.acalentoong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.littlecats.acalentoong to javafx.fxml;
    exports com.littlecats.acalentoong;
}