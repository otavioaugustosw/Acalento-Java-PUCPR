package com.littlecats.acalentoong.view;

import com.littlecats.acalentoong.models.Event;
import com.littlecats.acalentoong.models.EventFileHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EventHomeView {

    public static void show(Stage stage) {

        // título
        Label title = new Label("Eventos cadastrados");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #033240;");

        // botão para criar novos eventos
        Button createButton = new Button("Criar evento");
        createButton.setOnAction(event -> CreateEventView.show(stage));
        createButton.setStyle("-fx-background-color: #CBDEE4");

        Button homeButton = new Button("Voltar para home");
        homeButton.setOnAction(e -> Home.show(stage));
        homeButton.setStyle("-fx-background-color: #CBDEE4");

        // tabela
        TableView<Event> tableView = new TableView<>();
        tableView.setPrefSize(800, 600);

        // coluna id
        TableColumn<Event, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getId().toString()));
        idCol.setPrefWidth(100);

        // coluna nome
        TableColumn<Event, String> nameCol = new TableColumn<>("Nome");
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));

        // coluna data
        TableColumn<Event, String> dateCol = new TableColumn<>("Data");
        dateCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDate()));

        // coluna horário
        TableColumn<Event, String> timeCol = new TableColumn<>("Hora");
        timeCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTime()));

        // coluna descrição
        TableColumn<Event, String> descriptionCol = new TableColumn<>("Descrição");
        descriptionCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDescription()));

        // coluna capacidade maxina
        TableColumn<Event, String> capacityCol = new TableColumn<>("Capacidade Máxima");
        capacityCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getMax_capacity())));

        // botões de editar e deletar
        TableColumn<Event, Void> buttonCol = new TableColumn<>("Botões");
        buttonCol.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button("Editar");
            private final Button deleteBtn = new Button("Excluir");
            private final HBox actionBox = new HBox(10, editBtn, deleteBtn);

            {
                editBtn.setStyle("-fx-background-color: #CBDEE4");
                editBtn.setOnAction(event -> {
                    Event selectedEvent = getTableView().getItems().get(getIndex());
                    EditEventFormView.show(stage, selectedEvent);
                });

                deleteBtn.setStyle("-fx-background-color: #CBDEE4");
                deleteBtn.setOnAction(event -> {
                    Event selectedEvent = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Deseja apagar esse evento?",  ButtonType.YES, ButtonType.NO);
                    confirm.setHeaderText("Deletar evento");
                    confirm.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            EventFileHandler.deleteEvent(selectedEvent.getId());
                            EventHomeView.show(stage);
                        }
                    });

                });
            }

            // se a celula estiver vazia não aparece os botões
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(actionBox);
                }
            }
        });

        // adicionar as colunas a tabela
        tableView.getColumns().addAll(idCol, nameCol, dateCol,  timeCol, descriptionCol, capacityCol,  buttonCol);

        // pegar os dados
        ArrayList<Event> events = EventFileHandler.readList();
        tableView.getItems().addAll(events);

        // organizar o layout
        VBox root = new VBox(15, title, homeButton, createButton, tableView);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #FFFEF2; -fx-font-size: 16px;");

        // cena
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Eventos");
        stage.setScene(scene);
        stage.show();

    }

}
