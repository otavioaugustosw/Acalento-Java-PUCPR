package com.littlecats.acalentoong.view;

import com.littlecats.acalentoong.models.User;
import com.littlecats.acalentoong.models.UserFileHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditUserFormView {

    public static void show(Stage stage, User user) {
        // título
        Label title = new Label("Editar usuário");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: #033240; -fx-font-weight: bold");

        // formulário

        // nome
        TextField name = new TextField(user.getName());
        name.setPromptText("Nome do usuário");

        // cpf
        TextField cpf = new TextField(user.getCpf());
        cpf.setPromptText("CPF");

        // data de nascimento
        DatePicker date = new DatePicker();
        date.setPromptText("Data");
        date.getEditor().setText(user.getBirthDay());

        // telefone
        TextField phone = new TextField(user.getCellphone());
        phone.setPromptText("Telefone");

        // email
        TextField email = new TextField(user.getEmail());
        email.setPromptText("Email");

        // senha
        TextField password = new TextField(user.getPassword());
        password.setPromptText("Senha");

        // cep
        TextField cep = new TextField(user.getCep());
        cep.setPromptText("Cep");

        // rua
        TextField street = new TextField(user.getStreet());
        street.setPromptText("Rua");

        // bairro
        TextField neighborhood = new TextField(user.getNeighborhood());
        neighborhood.setPromptText("Bairro");

        // numero
        TextField number = new TextField(String.valueOf(user.getNumber()));
        number.setPromptText("Numero");

        // complemento
        TextField complement = new TextField(user.getComplement());
        complement.setPromptText("Complemento");

        // cidade
        TextField city = new TextField(user.getCity());
        city.setPromptText("Cidade");

        // estado
        TextField state = new TextField(user.getState());
        state.setPromptText("Estado");

        // botões de salvar e voltar
        Button updateButton = new Button("Atualizar");
        updateButton.setStyle("-fx-background-color: #CBDEE4");
        Button cancelButton = new Button("Cancelar");
        cancelButton.setStyle("-fx-background-color: #CBDEE4");

        // ação do botão salvar
        updateButton.setOnAction(e -> {
            try {
                user.setName(name.getText());
                user.setCpf(cpf.getText());
                user.setBirthDay(date.getEditor().getText());
                user.setCellphone(phone.getText());
                user.setEmail(email.getText());
                user.setPassword(password.getText());
                user.setCep(cep.getText());
                user.setStreet(street.getText());
                user.setNeighborhood(neighborhood.getText());
                user.setNumber(Integer.parseInt(number.getText()));
                user.setComplement(complement.getText());
                user.setCity(city.getText());
                user.setState(state.getText());

                UserFileHandler.updateUser(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Usuário atualizado com sucesso <3");
                alert.getDialogPane().setStyle(
                        "-fx-font-size: 14px;" +
                                "-fx-background-color: #FFFEF2;"
                );
                alert.showAndWait();
                back(stage);
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro ao atualizar usuário: " + ex.getMessage()).show();
            }
        });

        // ação botão voltar
        cancelButton.setOnAction(e -> UserHomeView.show(stage));

        // criar formulário
        VBox form = new VBox(10, title, name, cpf, date, phone, email, password, cep, street, neighborhood, number, complement, city, state, updateButton, cancelButton);
        form.setPadding(new Insets(20));
        form.setPrefWidth(500);
        form.setStyle("-fx-background-color: #FFFEF2;");

        // criar a cena
        Scene scene = new Scene(form, 900, 600);
        stage.setTitle("Editar usuário");
        stage.setScene(scene);
        stage.show();

    }

    public static void back(Stage stage) {
        UserHomeView.show(stage);
    }
}

