package com.example.sdservidor.Actions;

import com.example.sdservidor.Authentication.JwtService;
import com.example.sdservidor.Authentication.ValidateUser;
import com.example.sdservidor.DAO.SessionDAO;
import com.example.sdservidor.DAO.UserDAO;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Helpers.HelperService;
import com.example.sdservidor.Models.JwtSession;
import com.example.sdservidor.Models.User;
import com.example.sdservidor.Receivers.*;
import com.example.sdservidor.Receivers.Data.*;
import com.example.sdservidor.Senders.*;
import com.example.sdservidor.Senders.Data.ListUsersData;
import com.example.sdservidor.Senders.Data.LoginData;
import com.example.sdservidor.Senders.Data.RequestAutoUserData;
import com.example.sdservidor.Senders.Data.RequestUserData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Objects;

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
                case "listar-usuarios":
                    response = handleListarUsers(data);
                    break;
                case "excluir-usuario":
                    response = handleRemoveUser(data);
                    break;
                case "pedido-edicao-usuario":
                    response = handleRequestUser(data);
                    break;
                case "edicao-usuario":
                    response = handleEditUser(data);
                    break;
                case "autocadastro-usuario":
                    response = handleRegisterUser(data);
                    break;
                case "pedido-proprio-usuario":
                    response = handleRequestAutoUser(data);
                    break;
                case "excluir-proprio-usuario":
                    response = handleRemoveSelfUser(data);
                    break;
                case "autoedicao-usuario":
                    response = handleSelfEditUser(data);
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

    public static BaseSender handleRegisterUser(String data) throws JsonProcessingException {
        RegisterUserReceiver request = RegisterUserReceiver.fromJson(data, RegisterUserReceiver.class);
        BaseSender response = null;

        request.getData().setSenha(JwtService.hashPassword(request.getData().getSenha()));

        RegisterUserData d = request.getData();

        User user = new User(d.getNome(), d.getEmail(), d.getSenha(), "user");

        String res = new UserDAO().addUser(user);

        if (res != null) {
            response = new ErrorSender(request.getAction(), res);
        } else {
            response = new SuccessSender(request.getAction(), "Usuário cadastrado com sucesso!");
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

                String res = new UserDAO().addUser(user);

                if (res != null) {
                    response = new ErrorSender(request.getAction(), res);
                } else {
                    response = new SuccessSender(request.getAction(), "Usuário cadastrado com sucesso!");
                }
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
                String token = JwtService.createJwt(String.valueOf(user.getId()), Objects.equals(user.getType(), "admin"));

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

    private static BaseSender handleListarUsers(String data) throws JsonProcessingException {
        ListUsersReceiver request = ListUsersReceiver.fromJson(data, ListUsersReceiver.class);
        BaseSender response = null;

        long id = HelperService.getUserIdFromToken(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                UserDAO dao = new UserDAO();

                List<User> users = dao.getAllUsers();

                ListUsersData senderData = new ListUsersData(users);

                response = new ListUsersSender(senderData);
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
        }

        return response;
    }

    private static BaseSender handleRequestAutoUser(String data) throws JsonProcessingException {
        RequestAutoUserReceiver request = RequestAutoUserReceiver.fromJson(data, RequestAutoUserReceiver.class);

        BaseSender response = null;

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        UserDAO dao = new UserDAO();

        User user = dao.getUserById(id);

        if (user == null) {
            response = new ErrorSender(request.getAction(), "Usuário não encontrado.");
        } else {
            RequestAutoUserData responseData = new RequestAutoUserData(user);

            response = new RequestAutoUserSender(responseData);
        }

        return response;
    }

    private static BaseSender handleRequestUser(String data) throws JsonProcessingException {
        RequestUserReceiver request = RequestUserReceiver.fromJson(data, RequestUserReceiver.class);

        BaseSender response = null;

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                UserDAO dao = new UserDAO();

                User user = dao.getUserById(request.getData().getUserId());

                RequestUserData responseData = new RequestUserData(user);

                response = new RequestUserSender(responseData);
            } else {
                response = new ErrorSender(request.getAction(), "Não autorizado.");
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
        }

        return response;
    }

    private static BaseSender handleSelfEditUser(String data) throws JsonProcessingException {
        SelfEditUserReceiver request = SelfEditUserReceiver.fromJson(data, SelfEditUserReceiver.class);

        BaseSender response = null;

        if (request.getData().getSenha() != null) {
            request.getData().setSenha(JwtService.hashPassword(request.getData().getSenha()));
        }

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());


        SelfEditUserData d = request.getData();

        if (id != d.getId()) {
            return response = new ErrorSender(request.getAction(), "Ação não autorizada.");
        }

        UserDAO dao = new UserDAO();

        User user = dao.getUserById(d.getId());

        user.setEmail(d.getEmail());
        user.setName(d.getNome());

        if (d.getSenha() != null) {
            user.setPassword(d.getSenha());
        }

        String res = dao.updateUser(user);

        if (res != null) {
            response = new ErrorSender(request.getAction(), res);
        } else {
            response = new SuccessSender(request.getAction(), "Usuário atualizado com sucesso!");
        }

        return response;
    }

    private static BaseSender handleEditUser(String data) throws  JsonProcessingException {
        EditUserReceiver request = EditUserReceiver.fromJson(data, EditUserReceiver.class);
        BaseSender response = null;

        if (request.getData().getSenha() != null) {
            request.getData().setSenha(JwtService.hashPassword(request.getData().getSenha()));
        }

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                EditUserData d = request.getData();

                UserDAO dao = new UserDAO();

                User user = dao.getUserById(d.getUserId());

                user.setEmail(d.getEmail());
                user.setName(d.getNome());
                user.setType(d.getTipo());

                if (d.getSenha() != null) {
                    user.setPassword(d.getSenha());
                }

                String res = dao.updateUser(user);

                if (res != null) {
                    response = new ErrorSender(request.getAction(), res);
                } else {
                    response = new SuccessSender(request.getAction(), "Usuário atualizado com sucesso!");
                }
            } else {
                response = new ErrorSender(request.getAction(), "Ação não autorizada.");
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
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

    private static BaseSender handleRemoveSelfUser(String data) throws  JsonProcessingException {
        RemoveSelfUserReceiver request = RemoveSelfUserReceiver.fromJson(data, RemoveSelfUserReceiver.class);

        BaseSender response = null;

        UserDAO dao = new UserDAO();

        User user = new UserDAO().getUserByEmail(request.getData().getEmail());

        if (user != null) {
            boolean isValid = JwtService.authenticate(user, request.getData().getPassword());

            if (isValid) {

                SessionDAO sessionDAO = new SessionDAO();

                List<JwtSession> sessions = sessionDAO.getSessionsByUserId(user.getId());

                sessions.forEach(sessionDAO::delete);

                dao.deleteUser(user);

                response = new SuccessSender(request.getAction(), "Usuário removido com sucesso!");
            } else {
                response = handleError(request.getAction(), "Credenciais incorretas!");
            }
        } else {
            response = handleError(request.getAction(), "Credenciais incorretas!");
        }

        return response;
    }

    private static BaseSender handleRemoveUser(String data) throws JsonProcessingException {
        RemoveUserReceiver request = RemoveUserReceiver.fromJson(data, RemoveUserReceiver.class);

        BaseSender response = null;

        UserDAO dao = new UserDAO();

        User user = dao.getUserById(request.getData().getUserId());

        if (user != null) {
            dao.deleteUser(user);

            response = new SuccessSender(request.getAction(), "Usuário removido com sucesso!");
        } else {
            response = new ErrorSender(request.getAction(), "Usuário não encontrado.");
        }

        return response;
    }

    private static UnknownActionSender handleUnknownAction(String action) {
        return new UnknownActionSender(action);
    }
    private static ErrorSender handleError(String action, String message) {
        return new ErrorSender(action, message);
    }

}
