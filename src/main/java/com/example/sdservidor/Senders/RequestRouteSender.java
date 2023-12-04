package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.RequestRouteData;
import com.example.sdservidor.Senders.Data.RequestSegmentData;

public class RequestRouteSender extends BaseSender {
    public RequestRouteSender(RequestRouteData data) {
        super(Actions.REQUEST_ROUTE, data, "Rota recuperada com sucesso!", false);
    }

    public RequestRouteSender() {
        super();
    }
}
