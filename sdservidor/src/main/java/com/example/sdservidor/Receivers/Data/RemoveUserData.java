package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;

public class RemoveUserData extends BaseData {
    private String token;

    private long userId;

    public RemoveUserData(String token, long userId) {
        this.token = token;
        this.userId = userId;
    }

    public RemoveUserData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
