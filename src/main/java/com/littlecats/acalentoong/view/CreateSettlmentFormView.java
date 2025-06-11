package com.littlecats.acalentoong.view;

import com.littlecats.acalentoong.models.Settlement;
import com.littlecats.acalentoong.models.SettlementFileHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateSettlmentFormView {

    public static void show(Stage stage) {

        Label title = new Label("Criar Assentamento");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: #033240; -fx-font-weight: bold;");

        TextField name = new TextField();
        name.setPromptText("Nome do Assentamento");

        TextField nFamily = new TextField();
        nFamily.setPromptText("Número de famílias");

        TextField street = new TextField();
        street.setPromptText("Rua do Assentamento");

        TextField number = new TextField();
        number.setPromptText("Numero do Assentamento");

        TextField neighborhood = new TextField();
        neighborhood.setPromptText("Bairro do Assentamento");

        TextField state = new TextField();
        state.setPromptText("Estado do Assentamento");

        TextField city = new TextField();
        city.setPromptText("Cidade");

        TextField cep = new TextField();
        cep.setPromptText("CEP do Assentamento");

        // botões de salvar e voltar
        Button saveButton = new Button("Salvar");
        saveButton.setStyle("-fx-background-color: #CBDEE4");
        Button backButton = new Button("Voltar");
        backButton.setStyle("-fx-background-color: #CBDEE4");

        saveButton.setOnAction(e -> {
            try {
                Settlement settlement = new Settlement(
                        name.getText(),
                        Integer.parseInt(nFamily.getText()),
                        street.getText(),
                        number.getText(),
                        neighborhood.getText(),
                        state.getText(),
                        city.getText(),
                        cep.getText()
                );
                SettlementFileHandler.createSettlement(settlement);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Assentamento criado com sucesso <3");
                alert.showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro ao criar assentamento: " + ex.getMessage()).show();
            }
        });

        // ação botão voltar
        backButton.setOnAction(event -> {
            SettlementHomeView.show(stage);
        });

        // criar formulário
        VBox form = new VBox(10, title, name, nFamily, street, number, neighborhood, state, city, cep, saveButton, backButton);
        form.setPadding(new Insets(20));
        form.setPrefWidth(500);
        form.setStyle("-fx-background-color: #FFFEF2;");

        // criar a cena
        Scene scene = new Scene(form, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Cadastrar evento");

    }
}
