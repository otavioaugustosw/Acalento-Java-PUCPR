module com.littlecats.acalentoong {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.littlecats.acalentoong;
    opens com.littlecats.acalentoong to javafx.fxml;
    exports com.littlecats.acalentoong.controller;
    opens com.littlecats.acalentoong.controller to javafx.fxml;
    exports com.littlecats.acalentoong.models;
    opens com.littlecats.acalentoong.models to javafx.fxml;
}