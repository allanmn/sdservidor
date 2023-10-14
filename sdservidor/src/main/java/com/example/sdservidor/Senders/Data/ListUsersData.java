package com.example.sdservidor.Senders.Data;

import com.example.sdservidor.Models.User;

import java.util.List;

public class ListUsersData extends BaseData {
    private List<User> usuarios;

    public ListUsersData(List<User> usuarios) {
        this.usuarios = usuarios;
    }

    public ListUsersData() {}

    public List<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }
}
