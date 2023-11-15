package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Helpers.Validators;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePointData extends BaseData {
    private String token;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("obs")
    private String obs;

    public CreatePointData(String nome, String obs, String token) {
        this.token = token;
        this.nome = nome;
        this.obs = obs;
    }

    public CreatePointData() {}

    public boolean validate() throws ValidationException {
        if (nome == null || nome.isEmpty()) {
            throw  new ValidationException("Nome é obrigatório");
        }

        return true;
    }



    public String getToken() {
        return token;
    }

    public String getNome() {
        return nome;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
