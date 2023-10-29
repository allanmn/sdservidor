package com.example.sdservidor.Senders.Data;

import com.example.sdservidor.Models.User;

import java.util.List;

public class RequestUserData extends BaseData {
    private User user;

    public RequestUserData(User user) {
        this.user = user;
    }

    public RequestUserData() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
