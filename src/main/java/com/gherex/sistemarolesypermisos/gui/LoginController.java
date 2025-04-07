package com.gherex.sistemarolesypermisos.gui;

import com.gherex.sistemarolesypermisos.logic.LogicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    public void handleClean(ActionEvent actionEvent) {
        usernameField.setText("");
        passwordField.setText("");
        textAreaField.setText("");
    }

    public void handleLogin(ActionEvent actionEvent) {

        String message;
        String username = usernameField.getText();
        String password = passwordField.getText();
        message = logicControl.validateUser(username, password);

        textAreaField.setText(message);

    }
}
