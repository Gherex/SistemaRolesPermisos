package com.gherex.sistemarolesypermisos.logic;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private String rolName;
    private String description;
    @OneToMany(mappedBy = "rol")
    private List<User> userList;

    public Rol() {
    }

    public Rol(String rolName, String description, List<User> userList) {
        this.rolName = rolName;
        this.description = description;
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return rolName;
    }

}
