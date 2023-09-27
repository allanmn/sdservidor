package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Receivers.Data.LoginData;

public class UnknownActionSender extends BaseSender {
    public UnknownActionSender(String action) {
        super(action);

        this.setMessage("Ação " + action + " não encontrada.");
    }

    public UnknownActionSender() {
        super();
    }
}
