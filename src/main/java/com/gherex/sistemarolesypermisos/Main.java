package com.gherex.sistemarolesypermisos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga el archivo FXML desde resources
        Parent root = FXMLLoader.load(
                getClass().getResource("/views/AdminPanel.fxml")
        ); // "/views/Login.fxml"

        primaryStage.setTitle("AdminPanel");
        primaryStage.setScene(new Scene(root, 400, 600)); // 350 600
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}