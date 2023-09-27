package com.example.sdservidor.Senders.Data;

public class LoginData extends BaseData {
    private String token;

    public LoginData(String token) {
        this.token = token;
    }

    public LoginData () {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
