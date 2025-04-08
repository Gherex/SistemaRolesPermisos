package com.gherex.sistemarolesypermisos.logic;

import com.gherex.sistemarolesypermisos.persistence.PersistenceController;

import java.util.List;

public class LogicController {

    PersistenceController persisControl;
    String username;

    public LogicController() {
        persisControl = new PersistenceController();
    }

    public String validateUser(String username, String password) {
        List<User> userList = persisControl.getUsers();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user.getRol().getRolName();
                }
            }
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<User> getAllUsers() {
        return persisControl.getUsers();
    }

    public List<Rol> getAllRoles() {
        return persisControl.getRoles();
    }

    public void createUser(User newUser) {
        persisControl.createUser(newUser);
    }

    public void deleteUser(int id) {
        persisControl.deleteUser(id);
    }

    public void editUser(User editedUser) {
        persisControl.editUser(editedUser);
    }
}