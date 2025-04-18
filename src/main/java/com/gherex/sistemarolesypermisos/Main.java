package com.gherex.sistemarolesypermisos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL window = getClass().getResource("/views/Login.fxml");

        if (window != null) {
            Parent root = FXMLLoader.load(window);
            primaryStage.setTitle("Login");
            primaryStage.setScene((new Scene(root)));
            primaryStage.show();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}