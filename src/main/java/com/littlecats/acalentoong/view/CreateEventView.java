package com.littlecats.acalentoong.view;

import com.littlecats.acalentoong.models.Event;
import com.littlecats.acalentoong.models.EventFileHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateEventView {

    public static void show(Stage stage) {

        // título
        Label title = new Label("Cadastrar novo evento");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: #033240; -fx-font-weight: bold;");

        // formulário

        // nome
        TextField name = new TextField();
        name.setPromptText("Nome do evento");

        // data
        DatePicker date = new DatePicker();

        // horário
        TextField time = new TextField();
        time.setPromptText("Hora do evento");

        // assentamento
        TextField settlement = new TextField();
        settlement.setPromptText("Assentamento do evento");

        // capacidade máxima
        TextField capacity = new TextField();
        capacity.setPromptText("Capacidade do evento");

        // descrição
        TextField description = new TextField();
        description.setPromptText("Descrição");

        // botões de salvar e voltar
        Button saveButton = new Button("Salvar");
        saveButton.setStyle("-fx-background-color: #CBDEE4");
        Button backButton = new Button("Voltar");
        backButton.setStyle("-fx-background-color: #CBDEE4");

        // ação do botão salvar
        saveButton.setOnAction(event -> {
            try {
                Event newEvent = new Event(
                        name.getText(),
                        date.getValue().toString(),
                        time.getText(),
                        settlement.getText(),
                        Integer.parseInt(capacity.getText()),
                        description.getText()
                );
                EventFileHandler.createEvent(newEvent);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Evento criado com sucesso <3");
                alert.showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro ao criar evento: " + ex.getMessage()).show();
            }
        });

        // ação botão voltar
        backButton.setOnAction(event -> {
            EventHomeView.show(stage);
        });

        // criar formulário
        VBox form = new VBox(10, title, name, date, time, settlement, capacity, description, saveButton, backButton);
        form.setPadding(new Insets(20));
        form.setPrefWidth(500);
        form.setStyle("-fx-background-color: #FFFEF2;");

        // criar a cena
        Scene scene = new Scene(form, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Cadastrar evento");

    }
}
