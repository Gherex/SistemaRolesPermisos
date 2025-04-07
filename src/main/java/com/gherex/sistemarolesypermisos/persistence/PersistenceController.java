package com.gherex.sistemarolesypermisos.persistence;

import com.gherex.sistemarolesypermisos.logic.Rol;
import com.gherex.sistemarolesypermisos.logic.User;

import java.util.List;

public class PersistenceController {

    UserJpaController userJpaC = new UserJpaController();
    RolJpaController rolJpaC = new RolJpaController();

    public List<User> getUsers() {
        return userJpaC.findAllUsers();
    }

    public List<Rol> getRols() {
        return rolJpaC.findAllRoles();
    }
}
