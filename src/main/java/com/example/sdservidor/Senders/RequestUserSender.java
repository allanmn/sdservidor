package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.ListUsersData;
import com.example.sdservidor.Senders.Data.RequestUserData;

public class RequestUserSender extends BaseSender {
    public RequestUserSender(RequestUserData data) {
        super(Actions.REQUEST_USER, data, "Usuário recuperado com sucesso!", false);
    }

    public RequestUserSender() {
        super();
    }
}
