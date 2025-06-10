package com.littlecats.acalentoong.view;


import com.littlecats.acalentoong.models.Settlement;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Home {

    public static void show(Stage stage) {

        // título
        Label title = new Label("Bem vindo ao Acalento!!");
        title.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-text-fill: #033240;");

        // eventos
        Button eventBtn = new Button("Gerenciar eventos");
        eventBtn.setPrefWidth(200);
        eventBtn.setOnAction(event -> EventHomeView.show(stage));
        eventBtn.setStyle("-fx-background-color: #CBDEE4");
        eventBtn.setPadding(new Insets(10, 10, 10, 10));


        // assentamentos
        Button settlementBtn = new Button("Gerenciar assentamentos");
        settlementBtn.setPrefWidth(200);
        settlementBtn.setOnAction(s -> SettlementHomeView.show(stage));
        settlementBtn.setStyle("-fx-background-color: #CBDEE4");
        settlementBtn.setPadding(new Insets(10, 10, 10, 10));

        // usuários
        Button userBtn = new Button("Gerenciar usuarios");
        userBtn.setPrefWidth(200);
        userBtn.setOnAction(u -> UserHomeView.show(stage));
        userBtn.setStyle("-fx-background-color: #CBDEE4");
        userBtn.setPadding(new Insets(10, 10, 10, 10));

        VBox root = new VBox(title, eventBtn, settlementBtn, userBtn);
        VBox.setMargin(eventBtn, new Insets(10, 10, 10, 10));
        VBox.setMargin(userBtn, new Insets(10, 10, 10, 10));
        VBox.setMargin(settlementBtn, new Insets(10, 10, 10, 10));
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #FFFEF2; -fx-alignment: center;");

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Tela Inicial");
        stage.setScene(scene);
        stage.show();
    }

}
