package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.RequestPointData;

public class RequestPointSender extends BaseSender {
    public RequestPointSender(RequestPointData data) {
        super(Actions.REQUEST_POINT, data, "Ponto recuperado com sucesso!", false);
    }

    public RequestPointSender() {
        super();
    }
}
