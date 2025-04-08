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

public class ModifyUserController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private ComboBox<Rol> roleComboBox;

    private LogicController logicControl;
    private User user;

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
    private void handleUpdate(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String pass1 = passwordField.getText();
        String pass2 = repeatPasswordField.getText();
        Rol rol = roleComboBox.getValue();

        user.setUsername(username);
        user.setPassword(pass1);
        user.setRol(rol);

        if (username.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || rol == null) {
            showAlert("Todos los campos son obligatorios.");
            return;
        }

        if (!pass1.equals(pass2)) {
            showAlert("Las contraseñas no coinciden.");
            return;
        }

        logicControl.editUser(user);
        showAlert("Usuario modificado correctamente.");
        handleClear();
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void setUser(User selectedUser) {
        this.user = selectedUser;
    }

    public void initData() {
        usernameField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
        repeatPasswordField.setText(user.getPassword());
        roleComboBox.setValue(user.getRol());
    }
}