package com.littlecats.acalentoong.controller;

import com.littlecats.acalentoong.models.Event;
import com.littlecats.acalentoong.models.EventFileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class UpdateEventFormController {
    private Event event;

    @FXML private TextField nameField;
    @FXML private DatePicker dateField;
    @FXML private TextField timeField;
    @FXML private TextField settlementField;
    @FXML private TextField capacityField;
    @FXML private TextField descriptionField;

    @FXML public void setEvent(Event event) {
        this.event = event;
        nameField.setText(event.getName());
        dateField.setValue(LocalDate.parse(event.getDate()));
        timeField.setText(event.getTime());
        settlementField.setText(event.getSettlement());
        descriptionField.setText(event.getDescription());
        capacityField.setText(String.valueOf(event.getMax_capacity()));
    }


    @FXML public void updateEvent() {
        try {
            event.setName(nameField.getText());
            event.setDate(dateField.getValue().toString());
            event.setTime(timeField.getText());
            event.setSettlement(settlementField.getText());
            event.setDescription(descriptionField.getText());
            event.setMax_capacity(Integer.parseInt(capacityField.getText()));

            EventFileHandler.updateEvent(event);
            back();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao atualizar evento: " + e.getMessage()).show();
        }
    }

    @FXML
    public void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/littlecats/acalentoong/EventView.fxml"));
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load(), 800, 600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
