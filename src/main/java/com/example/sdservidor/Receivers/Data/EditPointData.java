package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Helpers.Validators;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditPointData extends BaseData {
    private String token;

    @JsonProperty("ponto_id")
    private long pontoId;

    private String name;

    private String obs;

    public EditPointData(long pontoId, String name, String obs, String token) {
        this.pontoId = pontoId;
        this.token = token;
        this.name = name;
        this.obs = obs;
    }

    public EditPointData() {}

    public boolean validate() throws ValidationException {
        if (name == null || name.isEmpty()) {
            throw  new ValidationException("Nome é obrigatório");
        }

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

    public String getObs() {
        return obs;
    }

    public String getName() {
        return name;
    }

    public void setPontoId(long pontoId) {
        this.pontoId = pontoId;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setName(String name) {
        this.name = name;
    }
}
