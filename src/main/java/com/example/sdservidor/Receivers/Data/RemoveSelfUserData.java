package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Helpers.Validators;

public class RemoveSelfUserData extends BaseData {
    private String email;
    private String password;

    private String token;

    public RemoveSelfUserData(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public RemoveSelfUserData() {}

    public boolean validate() throws ValidationException {
        if (email == null || email.isEmpty()) {
            throw  new ValidationException("E-mail é obrigatório");
        }

        if (password == null || password.isEmpty()) {
            throw  new ValidationException("Senha é obrigatório");
        }

        if (!Validators.isValidEmail(email)) {
            throw  new ValidationException("E-mail inválido");
        }

        if (!Validators.isValidPassword(password)) {
            throw  new ValidationException("Senha deve ter 6 digitos");
        }

        if (token == null || token.isEmpty()) {
            throw  new ValidationException("Token é obrigatório.");
        }

        return true;
    }



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
