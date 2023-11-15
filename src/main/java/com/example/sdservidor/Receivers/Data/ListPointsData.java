package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;

public class ListPointsData extends BaseData {
    private String token;

    public ListPointsData(String token) {
        this.token = token;
    }

    public ListPointsData() {}

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
