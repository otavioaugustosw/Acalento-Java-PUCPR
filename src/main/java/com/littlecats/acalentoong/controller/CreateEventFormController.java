package com.littlecats.acalentoong.controller;

import com.littlecats.acalentoong.models.Event;
import com.littlecats.acalentoong.models.EventFileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class CreateEventFormController {
    @FXML private TextField nameField;
    @FXML private DatePicker dateField;
    @FXML private TextField timeField;
    @FXML private TextField settlementField;
    @FXML private TextField capacityField;
    @FXML private TextField descriptionField;

    @FXML void save() {
        try {
            Event event = new Event(
                    nameField.getText(),
                    dateField.getValue().toString(),                    timeField.getText(),
                    settlementField.getText(),
                    Integer.parseInt(capacityField.getText()),
                    descriptionField.getText()
            );
            EventFileHandler.createEvent(event);
            back();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao criar o evento: " + e.getMessage()).show();
        }
    }

    @FXML private void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/littlecats/acalentoong/EventView.fxml"));
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load(), 800, 600));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
