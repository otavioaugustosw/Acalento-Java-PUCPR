package com.littlecats.acalentoong.controller;

import com.littlecats.acalentoong.models.Event;
import com.littlecats.acalentoong.models.EventFileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EventController {
    @FXML
    private TableView<Event> tableEvent;
    @FXML
    private TableColumn<Event, Integer> idColumn;
    @FXML
    private TableColumn<Event, String> nameColumn;
    @FXML
    private TableColumn<Event, String> dateColumn;
    @FXML
    private TableColumn<Event, String> timeColumn;
    @FXML
    private TableColumn<Event, String> settlementColumn;
    @FXML
    private TableColumn<Event, String> descriptionColumn;
    @FXML
    private TableColumn<Event, Integer> capacityColumn;
    @FXML
    private TableColumn<Event, Void> btnAction;

    private ObservableList<Event> events = FXCollections.observableArrayList(EventFileHandler.readList());

    @FXML
    private void initialize() {
        tableEvent.setItems(events);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        settlementColumn.setCellValueFactory(new PropertyValueFactory<>("settlement"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("max_capacity"));
        addBtn();
    }

    private void addBtn() {
        btnAction.setCellFactory(param -> new TableCell<>() {
            private final Button deleteBtn = new Button("Deletar");
            private final Button editBtn = new Button("Editar");

            {
                editBtn.setOnAction(event -> {
                    Event e = getTableView().getItems().get(getIndex());
                    openUpdateScene(e);
                });

                deleteBtn.setOnAction(event -> {
                    Event e = getTableView().getItems().get(getIndex());
                    EventFileHandler.deleteEvent(e.getId());
                    events.remove(e);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(5, deleteBtn, editBtn);
                    setGraphic(box);
                }
            }
        });
    }

    @FXML
    private void openUpdateScene(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/littlecats/acalentoong/EditEvent.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            UpdateEventFormController controller = loader.getController();
            controller.setEvent(event);
            Stage stage = (Stage) tableEvent.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openCreateScene() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/littlecats/acalentoong/CreateEvent.fxml"));
            Stage stage = (Stage) tableEvent.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
