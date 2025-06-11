package com.littlecats.acalentoong.view;

import com.littlecats.acalentoong.models.Settlement;
import com.littlecats.acalentoong.models.SettlementFileHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SettlementHomeView {

    public static void show(Stage stage) {

        // título
        Label title = new Label("Assentamentos cadastrados");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #033240;");

        // botão para criar novos assentamentos
        Button createButton = new Button("Criar assentamento");
        createButton.setOnAction(e -> CreateSettlmentFormView.show(stage));
        createButton.setStyle("-fx-background-color: #CBDEE4");

        Button homeButton = new Button("Voltar para home");
        homeButton.setOnAction(e -> Home.show(stage));
        homeButton.setStyle("-fx-background-color: #CBDEE4");

        // tabela
        TableView<Settlement> tableView = new TableView<>();
        tableView.setPrefSize(800, 600);

        // id
        TableColumn<Settlement, String> idCol = new TableColumn<>("id");
        idCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getId().toString()));
        idCol.setPrefWidth(100);

        // nome
        TableColumn<Settlement, String> nameCol = new TableColumn<>("Nome");
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNome()));

        // numero de familias
        TableColumn<Settlement, String> familyCol = new TableColumn<>("Número de famílias");
        familyCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getNumeroDeFamilias())));

        // rua
        TableColumn<Settlement, String> streetCol = new TableColumn<>("Rua");
        streetCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getRua()));

        // número
        TableColumn<Settlement, String> numberCol = new TableColumn<>("Nome");
        numberCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNumero()));

        // bairro
        TableColumn<Settlement, String> neighborCol = new TableColumn<>("Bairro");
        neighborCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getBairro()));

        // cidade
        TableColumn<Settlement, String> cityCol = new TableColumn<>("Cidade");
        cityCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCidade()));

        // estado
        TableColumn<Settlement, String> stateCol = new TableColumn<>("Estado");
        stateCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEstado()));

        // cep
        TableColumn<Settlement, String> cepCol = new TableColumn<>("CEO");
        cepCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCep()));

        // botões de editar e deletar
        TableColumn<Settlement, Void> buttonCol = new TableColumn<>("Ações");
        buttonCol.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button("Editar");
            private final Button deleteBtn = new Button("Excluir");
            private final HBox actionBox = new HBox(10, editBtn, deleteBtn);

            {
                editBtn.setStyle("-fx-background-color: #CBDEE4");
                editBtn.setOnAction(e -> {
                    Settlement selectedSettlement = getTableView().getItems().get(getIndex());
                    EditSettlementFormView.show(stage, selectedSettlement);
                });

                deleteBtn.setStyle("-fx-background-color: #CBDEE4");
                deleteBtn.setOnAction(e -> {
                    Settlement selectedSettlemt = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Deseja apagar esse assentamento?",  ButtonType.YES, ButtonType.NO);
                    confirm.setHeaderText("Deletar assentamento");
                    confirm.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            SettlementFileHandler.deleteSettlement(selectedSettlemt.getId());
                            SettlementHomeView.show(stage);
                        }
                    });

                });
            }

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

        // adicionar dado a tabela
        tableView.getColumns().addAll(idCol, nameCol, familyCol,  streetCol, numberCol, neighborCol, cityCol, stateCol, cepCol, buttonCol);

        // pegar os dados
        ArrayList<Settlement> settlements = SettlementFileHandler.fetchSettlements();
        tableView.getItems().addAll(settlements);

        // organizar o layout
        VBox root = new VBox(15, title, homeButton, createButton, tableView);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #FFFEF2; -fx-font-size: 16px;");

        // cena
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Assentamentos");
        stage.setScene(scene);
        stage.show();






    }
}
