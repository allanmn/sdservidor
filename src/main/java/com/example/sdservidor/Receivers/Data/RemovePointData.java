package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RemovePointData extends BaseData {
    private String token;

    @JsonProperty("ponto_id")
    private long pontoId;

    public RemovePointData(String token, long pontoId) {
        this.token = token;
        this.pontoId = pontoId;
    }

    public RemovePointData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getPontoId() {
        return pontoId;
    }

    public void setPontoId(long pontoId) {
        this.pontoId = pontoId;
    }
}
