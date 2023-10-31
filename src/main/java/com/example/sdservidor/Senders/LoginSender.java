package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.LoginData;

public class LoginSender extends BaseSender {
    public LoginSender(LoginData data) {
        super(Actions.LOGIN, data, "Login efetuado com sucesso!", false);
    }

    public LoginSender() {
        super();
    }
}
