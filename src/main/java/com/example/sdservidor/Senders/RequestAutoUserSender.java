package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.RequestAutoUserData;
import com.example.sdservidor.Senders.Data.RequestUserData;

public class RequestAutoUserSender extends BaseSender {
    public RequestAutoUserSender(RequestAutoUserData data) {
        super(Actions.REQUEST_AUTO_USER, data, "Usuário recuperado com sucesso!", false);
    }

    public RequestAutoUserSender() {
        super();
    }
}
