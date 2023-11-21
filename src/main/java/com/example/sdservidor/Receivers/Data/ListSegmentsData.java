package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;

public class ListSegmentsData extends BaseData {
    private String token;

    public ListSegmentsData(String token) {
        this.token = token;
    }

    public ListSegmentsData() {}

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
