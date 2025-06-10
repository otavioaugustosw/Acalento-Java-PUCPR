package com.littlecats.acalentoong;

import com.littlecats.acalentoong.view.Home;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Home.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}