package com.gherex.sistemarolesypermisos.gui;

import com.gherex.sistemarolesypermisos.logic.LogicController;
import com.gherex.sistemarolesypermisos.logic.Rol;
import com.gherex.sistemarolesypermisos.logic.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class LoadNewUserController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private ComboBox<Rol> roleComboBox;

    private LogicController logicControl;

    public void setLogicControl(LogicController logicControl) {
        this.logicControl = logicControl;
        loadRoles();
    }

    private void loadRoles() {
        List<Rol> roles = logicControl.getAllRoles();
        roleComboBox.setItems(FXCollections.observableArrayList(roles));
    }

    @FXML
    private void handleClear() {
        usernameField.clear();
        passwordField.clear();
        repeatPasswordField.clear();
        roleComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleSave(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String pass1 = passwordField.getText();
        String pass2 = repeatPasswordField.getText();
        Rol selectedRol = roleComboBox.getValue();

        if (username.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || selectedRol == null) {
            showAlert("Todos los campos son obligatorios.");
            return;
        }

        if (!pass1.equals(pass2)) {
            showAlert("Las contraseñas no coinciden.");
            return;
        }

        User newUser = new User(username, pass1, selectedRol);
        logicControl.createUser(newUser);
        showAlert("Usuario creado correctamente.");
        handleClear();
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
