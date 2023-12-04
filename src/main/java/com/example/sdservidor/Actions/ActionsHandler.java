package com.example.sdservidor.Actions;

import com.example.sdservidor.Authentication.JwtService;
import com.example.sdservidor.Authentication.ValidateUser;
import com.example.sdservidor.DAO.PointDAO;
import com.example.sdservidor.DAO.SegmentDAO;
import com.example.sdservidor.DAO.SessionDAO;
import com.example.sdservidor.DAO.UserDAO;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Helpers.HelperService;
import com.example.sdservidor.Models.JwtSession;
import com.example.sdservidor.Models.Point;
import com.example.sdservidor.Models.Segment;
import com.example.sdservidor.Models.User;
import com.example.sdservidor.Receivers.*;
import com.example.sdservidor.Receivers.Data.*;
import com.example.sdservidor.Senders.*;
import com.example.sdservidor.Senders.Data.ListPointsData;
import com.example.sdservidor.Senders.Data.ListSegmentsData;
import com.example.sdservidor.Senders.Data.ListUsersData;
import com.example.sdservidor.Senders.Data.LoginData;
import com.example.sdservidor.Senders.Data.RequestAutoUserData;
import com.example.sdservidor.Senders.Data.RequestPointData;
import com.example.sdservidor.Senders.Data.RequestSegmentData;
import com.example.sdservidor.Senders.Data.RequestUserData;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Objects;

public class ActionsHandler {

    public static BaseSender handleAction(String action, String data, String ip) {
        BaseSender response = null;

        try {
            switch (action) {
                case "login":
                    response = handleLogin(data, ip);
                    break;
                case "logout":
                    response = handleLogout(data);
                    break;
                case "cadastro-usuario":
                    response = handleCadastroUser(data);
                    break;
                case "cadastro-segmento":
                    response = handleCreateSegment(data);
                    break;
                case "listar-usuarios":
                    response = handleListarUsers(data);
                    break;
                case "listar-segmentos":
                    response = handleListSegments(data);
                    break;
                case "excluir-usuario":
                    response = handleRemoveUser(data);
                    break;
                case "excluir-segmento":
                    response = handleRemoveSegment(data);
                    break;
                case "excluir-ponto":
                    response = handleRemovePoint(data);
                    break;
                case "pedido-edicao-usuario":
                    response = handleRequestUser(data);
                    break;
                case "pedido-edicao-segmento":
                    response = handleRequestSegment(data);
                    break;
                case "pedido-edicao-ponto":
                    response = handleRequestPoint(data);
                    break;
                case "edicao-usuario":
                    response = handleEditUser(data);
                    break;
                case "edicao-segmento":
                    response = handleEditSegment(data);
                    break;
                case "edicao-ponto":
                    response = handleEditPoint(data);
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
                case "cadastro-ponto":
                    response = handleCreatePoint(data);
                    break;
                case "listar-pontos":
                    response = handleListPoints(data);
                    break;
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

    private static BaseSender handleCreatePoint(String data) throws JsonProcessingException {
        CreatePointReceiver request = CreatePointReceiver.fromJson(data, CreatePointReceiver.class);
        BaseSender response = null;

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                CreatePointData d = request.getData();

                Point point = new Point(d.getNome(), d.getObs());

                String res = new PointDAO().addPoint(point);

                if (res != null) {
                    response = new ErrorSender(request.getAction(), res);
                } else {
                    response = new SuccessSender(request.getAction(), "Ponto cadastrado com sucesso!");
                }
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
        }

        return response;
    }

    private static BaseSender handleCreateSegment(String data) throws JsonProcessingException {
        CreateSegmentReceiver request = CreateSegmentReceiver.fromJson(data, CreateSegmentReceiver.class);
        BaseSender response = null;

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                CreateSegmentData d = request.getData();

                Segment segment = d.getSegmento();

                SegmentDAO segmentDAO = new SegmentDAO();

                String res = segmentDAO.saveOrUpdateSegment(segment);

                if (res != null) {
                    response = new ErrorSender(request.getAction(), res);
                } else {
                    response = new SuccessSender(request.getAction(), "Segmento cadastrado com sucesso!");
                }
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
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

    private static BaseSender handleLogin(String data, String ip) throws JsonProcessingException {
        LoginReceiver request = LoginReceiver.fromJson(data, LoginReceiver.class);
        BaseSender response = null;

        User user = new UserDAO().getUserByEmail(request.getData().getEmail());

        if (user != null) {
            boolean isValid = JwtService.authenticate(user, request.getData().getPassword());

            if (isValid) {
                String token = JwtService.createJwt(String.valueOf(user.getId()), Objects.equals(user.getType(), "admin"));

                JwtSession session = null;

                SessionDAO sessionDAO = new SessionDAO();

                session = sessionDAO.getSessionByIp(ip);

                if (session == null) {
                    session = new JwtSession(user, token, ip);
                    sessionDAO.save(session);
                } else {
                    sessionDAO.updateJwtToken(session.getId(), token);
                }

                response = new LoginSender(new LoginData(token));
            } else {
                response = handleError(request.getAction(), "Credenciais incorretas!");
            }
        } else {
            response = handleError(request.getAction(), "Credenciais incorretas!");
        }

        return response;
    }

    private static BaseSender handleListPoints(String data) throws JsonProcessingException {
        ListPointsReceiver request = ListPointsReceiver.fromJson(data, ListPointsReceiver.class);

        BaseSender response = null;

        long id = HelperService.getUserIdFromToken(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                PointDAO dao = new PointDAO();

                List<Point> points = dao.getAllPoints();

                ListPointsData senderData = new ListPointsData(points);

                response = new ListPointsSender(senderData);
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
        }

        return response;
    }

    private static BaseSender handleListSegments(String data) throws JsonProcessingException {
        ListSegmentsReceiver request = ListSegmentsReceiver.fromJson(data, ListSegmentsReceiver.class);
        BaseSender response = null;

        long id = HelperService.getUserIdFromToken(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                SegmentDAO dao = new SegmentDAO();

                List<Segment> segments = dao.getAllSegments();

                ListSegmentsData senderData = new ListSegmentsData(segments);

                response = new ListSegmentsSender(senderData);
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
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

    private static BaseSender handleRequestPoint(String data) throws JsonProcessingException {
        RequestPointReceiver request = RequestPointReceiver.fromJson(data, RequestPointReceiver.class);

        BaseSender response = null;

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                PointDAO dao = new PointDAO();

                Point point = dao.getPointById(request.getData().getPontoId());

                RequestPointData senderData = new RequestPointData(point);

                response = new RequestPointSender(senderData);
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
        }

        return response;
    }

    private static BaseSender handleRequestSegment(String data) throws JsonProcessingException {
        RequestSegmentReceiver request = RequestSegmentReceiver.fromJson(data, RequestSegmentReceiver.class);

        BaseSender response = null;

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                SegmentDAO dao = new SegmentDAO();

                Segment segment = dao.getSegmentById(request.getData().getSegmentId());

                RequestSegmentData responseData = new RequestSegmentData(segment);

                response = new RequestSegmentSender(responseData);
            } else {
                response = new ErrorSender(request.getAction(), "Não autorizado.");
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
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

    private static BaseSender handleEditPoint(String data) throws JsonProcessingException {
        EditPointReceiver request = EditPointReceiver.fromJson(data, EditPointReceiver.class);
        BaseSender response = null;

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                EditPointData d = request.getData();

                PointDAO dao = new PointDAO();

                Point point = dao.getPointById(d.getPontoId());

                point.setObs(d.getObs());
                point.setName(d.getName());

                String res = dao.updatePoint(point);

                if (res != null) {
                    response = new ErrorSender(request.getAction(), res);
                } else {
                    response = new SuccessSender(request.getAction(), "Ponto atualizado com sucesso!");
                }
            } else {
                response = new ErrorSender(request.getAction(), "Ação não autorizada.");
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
        }

        return response;
    }

    private static BaseSender handleEditSegment(String data) throws  JsonProcessingException {
        EditSegmentReceiver request = EditSegmentReceiver.fromJson(data, EditSegmentReceiver.class);
        BaseSender response = null;

        long id = JwtService.getUserIdFromJwt(request.getData().getToken());

        try {
            if (ValidateUser.validate("admin", id)) {
                EditSegmentData d = request.getData();

                SegmentDAO dao = new SegmentDAO();

                Segment segment = dao.getSegmentById(d.getSegmentId());

                segment.setDirecao(d.getSegment().getDirecao());
                segment.setDistancia(d.getSegment().getDistancia());
                segment.setPonto_origem(d.getSegment().getPonto_origem());
                segment.setPonto_destino(d.getSegment().getPonto_destino());
                segment.setObs(d.getSegment().getObs());
                segment.setBloqueado(d.getSegment().getBloqueado());

                String res = dao.saveOrUpdateSegment(segment);

                if (res != null) {
                    response = new ErrorSender(request.getAction(), res);
                } else {
                    response = new SuccessSender(request.getAction(), "Segmento atualizado com sucesso!");
                }
            } else {
                response = new ErrorSender(request.getAction(), "Ação não autorizada.");
            }
        } catch (ValidationException e) {
            response = new ErrorSender(request.getAction(), e.getMessage());
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

    private static BaseSender handleRemoveSegment(String data) throws JsonProcessingException {
        RemoveSegmentReceiver request = RemoveSegmentReceiver.fromJson(data, RemoveSegmentReceiver.class);

        BaseSender response = null;

        SegmentDAO dao = new SegmentDAO();

        Segment segment = dao.getSegmentById(request.getData().getSegmentId());

        if (segment != null) {
            dao.deleteSegment(segment);

            response = new SuccessSender(request.getAction(), "Segmento removido com sucesso!");
        } else {
            response = new ErrorSender(request.getAction(), "Segmento não encontrado.");
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

    private static BaseSender handleRemovePoint(String data) throws JsonProcessingException {
        RemovePointReceiver request = RemovePointReceiver.fromJson(data, RemovePointReceiver.class);

        BaseSender response = null;

        PointDAO dao = new PointDAO();

        Point point = dao.getPointById(request.getData().getPontoId());

        if (point != null) {
            dao.deletePoint(point);

            response = new SuccessSender(request.getAction(), "Ponto removido com sucesso!");
        } else {
            response = new ErrorSender(request.getAction(), "Ponto não encontrado.");
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
