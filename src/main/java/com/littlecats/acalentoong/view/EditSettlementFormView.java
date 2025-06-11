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

public class EditSettlementFormView {

    public static void show(Stage stage, Settlement settlement) {

        Label title = new Label("Editar assentamentos");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: #033240; -fx-font-weight: bold;");

        TextField name = new TextField(settlement.getNome());
        name.setPromptText("Nome do Assentamento");

        TextField nFamily = new TextField(String.valueOf(settlement.getNumeroDeFamilias()));
        nFamily.setPromptText("Número de famílias");

        TextField street = new TextField(settlement.getRua());
        street.setPromptText("Rua do Assentamento");

        TextField number = new TextField(settlement.getNumero());
        number.setPromptText("Numero do Assentamento");

        TextField neighborhood = new TextField(settlement.getBairro());
        neighborhood.setPromptText("Bairro do Assentamento");

        TextField state = new TextField(settlement.getEstado());
        state.setPromptText("Estado do Assentamento");

        TextField city = new TextField(settlement.getCidade());
        city.setPromptText("Cidade");

        TextField cep = new TextField(settlement.getCep());
        cep.setPromptText("CEP do Assentamento");

        Button updateButton = new Button("Atualizar");
        updateButton.setStyle("-fx-background-color: #CBDEE4");
        Button cancelButton = new Button("Cancelar");
        cancelButton.setStyle("-fx-background-color: #CBDEE4");

        updateButton.setOnAction(e -> {
            try {
                settlement.setNome(name.getText());
                settlement.setNumeroDeFamilias(Integer.parseInt(nFamily.getText()));
                settlement.setRua(street.getText());
                settlement.setBairro(neighborhood.getText());
                settlement.setEstado(state.getText());
                settlement.setCidade(city.getText());
                settlement.setCep(cep.getText());

                SettlementFileHandler.updateSettlement(settlement);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Assentamento atualizado com sucesso <3");
                alert.getDialogPane().setStyle(
                        "-fx-font-size: 14px;" +
                                "-fx-background-color: #FFFEF2;"
                );
                alert.showAndWait();
                back(stage);
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro ao atualizar assentamento: " + ex.getMessage()).show();
            }
        });

        // ação botão voltar
        cancelButton.setOnAction(e -> SettlementHomeView.show(stage));

        // criar formulário
        VBox form = new VBox(10, title, name, nFamily, street, number, neighborhood, state, city, cep, updateButton, cancelButton);
        form.setPadding(new Insets(20));
        form.setPrefWidth(500);
        form.setStyle("-fx-background-color: #FFFEF2;");

        // criar a cena
        Scene scene = new Scene(form, 900, 600);
        stage.setTitle("Editar assentamento");
        stage.setScene(scene);
        stage.show();
    }

    public static void back(Stage stage) {
        SettlementHomeView.show(stage);
    }
}
