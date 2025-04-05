package com.gherex.mylogin.logic;

import com.gherex.mylogin.persistence.PersistenceController;
import com.mysql.cj.log.Log;

import java.util.List;

public class LogicController {

    PersistenceController persisControl;

    public LogicController() {
        persisControl = new PersistenceController();
    }

    public String validateUser(String username, String password) {
        List<User> userList = persisControl.getUsers();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return "Usuario y contraseña correctos. Bienvenido/a!";
                } else {
                    return "Contraseña incorrecta.";
                }
            }
        }
        return "Usuario no encontrado.";
    }

}