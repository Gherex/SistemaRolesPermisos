package com.gherex.sistemarolesypermisos.gui;

import com.gherex.sistemarolesypermisos.logic.LogicController;
import com.gherex.sistemarolesypermisos.logic.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class UserPanelController {

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

    public void reloadTable() {
        loadTable();
    }

    public void quit(ActionEvent actionEvent) {
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

}
