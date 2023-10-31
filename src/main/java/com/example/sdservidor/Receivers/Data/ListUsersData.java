package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;

public class ListUsersData extends BaseData {
    private String token;

    public ListUsersData(String token) {
        this.token = token;
    }

    public ListUsersData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
