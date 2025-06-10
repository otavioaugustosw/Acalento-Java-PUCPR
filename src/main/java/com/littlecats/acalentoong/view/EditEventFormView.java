package com.littlecats.acalentoong.view;

import com.littlecats.acalentoong.models.Event;
import com.littlecats.acalentoong.models.EventFileHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditEventFormView {

    public static void show(Stage stage, Event event) {

        // título
        Label title = new Label("Editar evento");
        title.setStyle("-fx-font-size: 20px; -fx-text-color: #033240; -fx-font-weight: bold;");

        // formulário

        // nome
        TextField name = new TextField(event.getName());
        name.setPromptText("Nome");

        // data
        DatePicker date = new DatePicker();
        date.setPromptText("Data");
        date.getEditor().setText(event.getDate());

        // horário
        TextField time = new TextField(event.getTime());
        time.setPromptText("Hora do evento");

        // assentamento
        TextField settlement = new TextField(event.getSettlement());
        settlement.setPromptText("Assentamento do evento");

        // capacidade máxima
        TextField capacity = new TextField(String.valueOf(event.getMax_capacity()));
        capacity.setPromptText("Capacidade do evento");

        // descrição
        TextField description = new TextField(event.getDescription());
        description.setPromptText("Descrição");

        // botões de salvar e voltar
        Button updateButton = new Button("Atualizar");
        updateButton.setStyle("-fx-background-color: #CBDEE4");
        Button cancelButton = new Button("Cancelar");
        cancelButton.setStyle("-fx-background-color: #CBDEE4");

        // ação do botão salvar
        updateButton.setOnAction(e -> {
            try {
                event.setName(name.getText());
                event.setDate(date.getEditor().getText());
                event.setTime(time.getText());
                event.setSettlement(settlement.getText());
                event.setMax_capacity(Integer.parseInt(capacity.getText()));
                event.setDescription(description.getText());

                EventFileHandler.updateEvent(event);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Evento atualizado com sucesso <3");
                alert.getDialogPane().setStyle(
                        "-fx-font-size: 14px;" +
                                "-fx-background-color: #FFFEF2;"
                );
                alert.showAndWait();
                back(stage);
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro ao atualizar evento: " + ex.getMessage()).show();
            }

        });

        // ação botão voltar
        cancelButton.setOnAction(e -> EventHomeView.show(stage));

        // criar formulário
        VBox form = new VBox(10, title, name, date, time, settlement, capacity, description, updateButton, cancelButton);
        form.setPadding(new Insets(20));
        form.setPrefWidth(500);
        form.setStyle("-fx-background-color: #FFFEF2;");

        // criar a cena
        Scene scene = new Scene(form, 900, 600);
        stage.setTitle("Editar evento");
        stage.setScene(scene);
        stage.show();
    }

    public static void back(Stage stage) {
        EventHomeView.show(stage);
    }
}

