package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Helpers.Validators;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditUserData extends BaseData {
    private String token;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String senha;

    @JsonProperty("type")
    private String tipo;

    public EditUserData(long userId, String nome, String tipo, String email, String password, String token) {
        this.userId = userId;
        this.email = email;
        this.senha = password;
        this.token = token;
        this.nome = nome;
        this.tipo = tipo;
    }

    public EditUserData() {}

    public boolean validate() throws ValidationException {
        if (email == null || email.isEmpty()) {
            throw  new ValidationException("E-mail é obrigatório");
        }

        if (!Validators.isValidEmail(email)) {
            throw  new ValidationException("E-mail inválido");
        }

        if (!Validators.isValidPassword(senha)) {
            throw  new ValidationException("Senha deve ter 6 digitos");
        }

        if (nome == null || nome.isEmpty()) {
            throw  new ValidationException("Nome é obrigatório");
        }

        if (tipo == null || tipo.isEmpty()) {
            throw  new ValidationException("Tipo é obrigatório");
        }

        return true;
    }



    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String password) {
        this.senha = password;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
