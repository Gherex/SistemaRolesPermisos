package com.gherex.sistemarolesypermisos.gui;

import com.gherex.sistemarolesypermisos.logic.LogicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextArea textAreaField;

    private final LogicController logicControl;

    public LoginController() {
        logicControl = new LogicController();
    }

    public void handleClean() {
        usernameField.setText("");
        passwordField.setText("");
        textAreaField.setText("");
    }

    @FXML
    public void handleLogin(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String rol = logicControl.validateUser(username, password);
        if (rol != null) {
            String URL, title;
            if (rol.equals("admin")) {
                URL = "/views/AdminPanel.fxml";
                title = "Admin Panel";
            } else {
                URL = "/views/UserPanel.fxml";
                title = "User Panel";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(URL));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent root;
            try {
                root = loader.load();
                if (rol.equals("admin")) {
                    AdminPanelController adminPanelController = loader.getController();
                    logicControl.setUsername(username);
                    adminPanelController.setLogicControl(logicControl);
                    adminPanelController.initData();
                } else {
                    UserPanelController userPanelController = loader.getController();
                    logicControl.setUsername(username);
                    userPanelController.setLogicControl(logicControl);
                    userPanelController.initData();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } else {
            textAreaField.setText("Usuario o contraseña incorrectos.");
        }
    }

}
