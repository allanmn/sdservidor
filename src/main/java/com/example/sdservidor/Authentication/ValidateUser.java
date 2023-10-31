package com.example.sdservidor.Authentication;

import com.example.sdservidor.DAO.UserDAO;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Models.User;

import java.util.Objects;

public class ValidateUser {
    public static boolean validate(String type, long userId) throws ValidationException {
        User user = new UserDAO().getUserById(userId);

        if (!Objects.equals(user.getType(), type)) {
            throw new ValidationException("Não autorizado");
        }

        return true;
    }
}
