package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.ListUsersData;
import com.example.sdservidor.Senders.Data.RequestUserData;

public class RequestUserSender extends BaseSender {
    public RequestUserSender(RequestUserData data) {
        super(Actions.LIST_USERS, data, "Usuário recuperado com sucesso!", false);
    }

    public RequestUserSender() {
        super();
    }
}
