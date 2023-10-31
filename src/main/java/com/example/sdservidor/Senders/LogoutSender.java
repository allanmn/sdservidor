package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.LoginData;

public class LogoutSender extends BaseSender {
    public LogoutSender() {
        super(Actions.LOGOUT, null, "Logout efetuado com sucesso!", false);
    }
}
