package com.gherex.mylogin.persistence;

import com.gherex.mylogin.logic.User;

import java.util.List;

public class PersistenceController {

    UserJpaController userJpaC = new UserJpaController();

    public List<User> getUsers() {
        return userJpaC.findAllUsers();
    }
}
