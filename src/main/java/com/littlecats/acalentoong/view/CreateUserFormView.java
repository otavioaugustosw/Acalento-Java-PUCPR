package com.littlecats.acalentoong.view;

import com.littlecats.acalentoong.models.User;
import com.littlecats.acalentoong.models.UserFileHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateUserFormView {

    public static void show(Stage stage) {
        // título
        Label title = new Label("Cadastrar novo evento");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: #033240; -fx-font-weight: bold;");

        // nome
        TextField name = new TextField();
        name.setPromptText("Nome do usuário");

        // cpf
        TextField cpf = new TextField();
        cpf.setPromptText("CPF");

        // data de nascimento
        DatePicker date = new DatePicker();

        // telefone
        TextField phone = new TextField();
        phone.setPromptText("Telefone");

        // email
        TextField email = new TextField();
        email.setPromptText("Email");

        // senha
        TextField password = new TextField();
        password.setPromptText("Senha");

        // cep
        TextField cep = new TextField();
        cep.setPromptText("Cep");

        // rua
        TextField street = new TextField();
        street.setPromptText("Rua");

        // bairro
        TextField neighborhood = new TextField();
        neighborhood.setPromptText("Bairro");

        // numero
        TextField number = new TextField();
        number.setPromptText("Numero");

        // complemento
        TextField complement = new TextField();
        complement.setPromptText("Complemento");

        // cidade
        TextField city = new TextField();
        city.setPromptText("Cidade");

        // estado
        TextField state = new TextField();
        state.setPromptText("Estado");

        // botões de salvar e voltar
        Button saveButton = new Button("Salvar");
        saveButton.setStyle("-fx-background-color: #CBDEE4");
        Button backButton = new Button("Voltar");
        backButton.setStyle("-fx-background-color: #CBDEE4");

        saveButton.setOnAction(e -> {
            try {
                User user = new User(
                        name.getText(),
                        cpf.getText(),
                        date.getValue().toString(),
                        phone.getText(),
                        email.getText(),
                        password.getText(),
                        cep.getText(),
                        street.getText(),
                        neighborhood.getText(),
                        Integer.parseInt(number.getText()),
                        complement.getText(),
                        city.getText(),
                        state.getText()
                );
                UserFileHandler.createUser(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Usuário criado com sucesso <3");
                alert.showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro ao criar usuário: " + ex.getMessage()).show();
            }
        });

        // ação botão voltar
        backButton.setOnAction(event -> {
            UserHomeView.show(stage);
        });

        // criar formulário
        VBox form = new VBox(10, title, name, cpf, date, phone, email, password, cep, street, neighborhood, number, complement, city, state, backButton, saveButton);
        form.setPadding(new Insets(20));
        form.setPrefWidth(500);
        form.setStyle("-fx-background-color: #FFFEF2;");

        // criar a cena
        Scene scene = new Scene(form, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Cadastrar usuário");
    }
}