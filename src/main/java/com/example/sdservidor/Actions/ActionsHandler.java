package com.example.sdservidor.Actions;

import com.example.sdservidor.Authentication.JwtService;
import com.example.sdservidor.Authentication.ValidateUser;
import com.example.sdservidor.DAO.SessionDAO;
import com.example.sdservidor.DAO.UserDAO;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Models.JwtSession;
import com.example.sdservidor.Models.User;
import com.example.sdservidor.Receivers.CreateUserReceiver;
import com.example.sdservidor.Receivers.Data.CreateUserData;
import com.example.sdservidor.Receivers.LoginReceiver;
import com.example.sdservidor.Receivers.LogoutReceiver;
import com.example.sdservidor.Senders.*;
import com.example.sdservidor.Senders.Data.LoginData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mindrot.jbcrypt.BCrypt;

public class ActionsHandler {

    public static BaseSender handleAction(String action, String data) {
        BaseSender response = null;

        try {
            switch (action) {
                case "login":
                    response = handleLogin(data);
                    break;
                case "logout":
                    response = handleLogout(data);
                    break;
                case "cadastro-usuario":
                    response = handleCadastroUser(data);
                    break;
                // Handle other actions as needed
                default:
                    response = handleUnknownAction(action);
            }
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
            response = handleError(action, ex.getMessage());
        }

        return response;
    }

    private static BaseSender handleCadastroUser(String data) throws JsonProcessingException {
        CreateUserReceiver request = CreateUserReceiver.fromJson(data, CreateUserReceiver.class);
        BaseSender response = null;

        request.getData().setSenha(JwtService.hashPassword(request.getData().getSenha()));

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                CreateUserData d = request.getData();

                User user = new User(d.getNome(), d.getEmail(), d.getSenha(), d.getTipo());

                new UserDAO().addUser(user);

                response = new SuccessSender(request.getAction(), "Usuário cadastrado com sucesso!");
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
        }

        return response;
    }

    private static BaseSender handleLogin(String data) throws JsonProcessingException {
        LoginReceiver request = LoginReceiver.fromJson(data, LoginReceiver.class);
        BaseSender response = null;

        User user = new UserDAO().getUserByEmail(request.getData().getEmail());

        if (user != null) {
            boolean isValid = JwtService.authenticate(user, request.getData().getPassword());

            if (isValid) {
                String token = JwtService.createJwt(String.valueOf(user.getId()));

                JwtSession session = new JwtSession(user, token);
                new SessionDAO().save(session);

                response = new LoginSender(new LoginData(token));
            } else {
                response = handleError(request.getAction(), "Credenciais incorretas!");
            }
        } else {
            response = handleError(request.getAction(), "Credenciais incorretas!");
        }

        return response;
    }

    private static BaseSender handleLogout(String data) throws JsonProcessingException {
        LogoutReceiver request = LogoutReceiver.fromJson(data, LogoutReceiver.class);
        BaseSender response = null;

        SessionDAO dao = new SessionDAO();

        JwtSession jwt = dao.getSessionByToken(request.getData().getToken());

        if (jwt != null) {
            dao.delete(jwt);
        }

        response = new LogoutSender();

        return response;
    }

    private static UnknownActionSender handleUnknownAction(String action) {
        return new UnknownActionSender(action);
    }
    private static ErrorSender handleError(String action, String message) {
        return new ErrorSender(action, message);
    }

}
