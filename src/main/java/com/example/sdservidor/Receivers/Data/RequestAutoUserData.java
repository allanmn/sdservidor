package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;

public class RequestAutoUserData extends BaseData {
    private String token;
    public RequestAutoUserData(String token) {
        this.token = token;
    }

    public RequestAutoUserData() {}

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
