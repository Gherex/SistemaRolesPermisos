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

    public List<Rol> getRoles() {
        return rolJpaC.getAllRoles();
    }

    public void createUser(User newUser) {
        userJpaC.create(newUser);
    }

    public void deleteUser(int id) {
        userJpaC.delete(id);
    }

    public void editUser(User editedUser) {
        userJpaC.update(editedUser);
    }
}
