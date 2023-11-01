package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Helpers.Validators;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SelfEditUserData extends BaseData {
    private String token;

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String senha;

    public SelfEditUserData(long userId, String nome, String tipo, String email, String password, String token) {
        this.id = userId;
        this.email = email;
        this.senha = password;
        this.token = token;
        this.nome = nome;
    }

    public SelfEditUserData() {}

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

    public String getNome() {
        return nome;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
