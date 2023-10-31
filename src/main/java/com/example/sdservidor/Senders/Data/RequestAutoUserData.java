package com.example.sdservidor.Senders.Data;

import com.example.sdservidor.Models.User;

public class RequestAutoUserData extends BaseData {
    private User user;

    public RequestAutoUserData(User user) {
        this.user = user;
    }

    public RequestAutoUserData() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
