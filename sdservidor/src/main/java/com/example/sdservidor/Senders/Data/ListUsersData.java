package com.example.sdservidor.Senders.Data;

import com.example.sdservidor.Models.User;

import java.util.List;

public class ListUsersData extends BaseData {
    private List<User> users;

    public ListUsersData(List<User> users) {
        this.users = users;
    }

    public ListUsersData() {}

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> usuarios) {
        this.users = usuarios;
    }
}
