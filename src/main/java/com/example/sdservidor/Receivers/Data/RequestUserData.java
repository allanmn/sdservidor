package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestUserData extends BaseData {
    private String token;

    @JsonProperty("user_id")
    private long userId;

    public RequestUserData(String token, long userId) {
        this.token = token;
        this.userId = userId;
    }

    public RequestUserData() {}

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
