package com.littlecats.acalentoong.view;

import com.littlecats.acalentoong.models.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserHomeView{

    public static void show(Stage stage) {

        // título
        Label title = new Label("Usuários cadastrados");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #033240");

        // botão para criar novos usuarios
        Button createButton = new Button("Criar usuário");
        createButton.setOnAction(e -> CreateUserFormView.show(stage));
        createButton.setStyle("-fx-background-color: #CBDEE4");

        Button homeButton = new Button("Voltar para home");
        homeButton.setOnAction(e -> Home.show(stage));
        homeButton.setStyle("-fx-background-color: #CBDEE4");

        // tabela
        TableView<User> tableView = new TableView<>();
        tableView.setPrefSize(800, 600);

        // id
        TableColumn<User, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getId().toString()));
        idCol.setPrefWidth(100);

        // nome
        TableColumn<User, String> nameCol = new TableColumn<>("Nome");
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));

        // cpf
        TableColumn<User, String> cpfCol = new TableColumn<>("Cpf");
        cpfCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCpf()));

        // data de nascimento
        TableColumn<User, String> dateCol = new TableColumn<>("Data");
        dateCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getBirthDay()));

        // telefone
        TableColumn<User, String> phoneCol = new TableColumn<>("Telefone");
        phoneCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCellphone()));

        // email
        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));

        // senha
        TableColumn<User, String> passwordCol = new TableColumn<>("Senha");
        passwordCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getPassword()));

        // cep
        TableColumn<User, String> cepCol = new TableColumn<>("CEP");
        cepCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCep()));

        // rua
        TableColumn<User, String> streetCol = new TableColumn<>("Rua");
        streetCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getStreet()));

        // bairro
        TableColumn<User, String> neighborCol = new TableColumn<>("Telefone");
        neighborCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNeighborhood()));

        // numero
        TableColumn<User, String> numberCol = new TableColumn<>("Número");
        numberCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getNumber())));

        // complemento
        TableColumn<User, String> complementCol = new TableColumn<>("Complemento");
        complementCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getComplement()));

        // cidade
        TableColumn<User, String> cityCol = new TableColumn<>("Cidade");
        cityCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCity()));

        // estado
        TableColumn<User, String> stateCol = new TableColumn<>("Estado");
        stateCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getState()));

        // botões de editar e deletar
        TableColumn<User, Void> buttonCol = new TableColumn<>("Botões");
        buttonCol.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button("Editar");
            private final Button deleteBtn = new Button("Excluir");
            private final HBox actionBox = new HBox(10, editBtn, deleteBtn);

            {
                editBtn.setStyle("-fx-background-color: #CBDEE4");
                editBtn.setOnAction(e -> {
                    User selectedUser = getTableView().getItems().get(getIndex());
                    EditUserFormView.show(stage, selectedUser);
                });

                deleteBtn.setStyle("-fx-background-color: #CBDEE4");
                deleteBtn.setOnAction(e -> {
                    User selectedUser = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Deseja apagar esse usuário?",  ButtonType.YES, ButtonType.NO);
                    confirm.setHeaderText("Deletar Usuário");
                    confirm.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            UserFileHandler.deleteUser(selectedUser.getId());
                            UserHomeView.show(stage);
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

        // adicionar as colunas a tabela
        tableView.getColumns().addAll(idCol, nameCol, cpfCol, dateCol, phoneCol, emailCol, passwordCol, cepCol, streetCol, neighborCol, numberCol, complementCol, cityCol, stateCol, buttonCol);

        // pegar os dados
        ArrayList<User> users = UserFileHandler.fetchUsers();
        tableView.getItems().addAll(users);

        // organizar o layout
        VBox root = new VBox(15, title, homeButton, createButton, tableView);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #FFFEF2; -fx-font-size: 16px;");

        // cena
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Usuários");
        stage.setScene(scene);
        stage.show();


    }
}
