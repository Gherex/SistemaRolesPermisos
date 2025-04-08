package com.gherex.sistemarolesypermisos.gui;

import com.gherex.sistemarolesypermisos.logic.LogicController;
import com.gherex.sistemarolesypermisos.logic.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminPanelController {

    @FXML
    private Label userLabel;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> colId;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colPassword;
    @FXML
    private TableColumn<User, String> colRoleId;

    private LogicController logicControl;

    public void setLogicControl(LogicController logicControl) {
        this.logicControl = logicControl;
    }

    public void initData() {
        String message = "Bienvenido, " + logicControl.getUsername();
        userLabel.setText(message);
        initializeColumns();
        loadTable();
    }

    private void initializeColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        // Mostrar rolName desde el objeto Rol
        colRoleId.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getRol().getRolName())
        );
    }

    public void loadTable() {
        List<User> userList = logicControl.getAllUsers();
        ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);
        userTable.setItems(observableUserList);
    }

    public void createUser() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoadNewUser.fxml"));
        Stage stage = new Stage();
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoadNewUserController loadNewUserController = loader.getController();
        loadNewUserController.setLogicControl(logicControl);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta de Usuarios");
        stage.show();
    }

    public void editUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/modifyUser.fxml"));
        Stage stage = new Stage();
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ModifyUserController modifyUserController = loader.getController();
        modifyUserController.setLogicControl(logicControl);
        modifyUserController.setUser(selectedUser);
        modifyUserController.initData();
        stage.setScene(new Scene(root));
        stage.setTitle("Modificación de usuario");
        stage.show();
    }

    public void deleteUser() {

        if (userTable.getItems().isEmpty()) {
            showAlert("La tabla está vacía.");
            return;
        }
        // Obtiene el usuario seleccionado
        User selectedUser = userTable.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            showAlert("Ningún usuario seleccionado.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText("¿Estás seguro que deseas eliminar al usuario?");
        alert.setContentText("Usuario: " + selectedUser.getUsername());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            logicControl.deleteUser(selectedUser.getId());
            loadTable();
        }

        showAlert("Usuario eliminado correctamente.");
        loadTable();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }


    public void reloadTable() {
        loadTable();
    }

    public void quit(ActionEvent actionEvent) {
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

}
