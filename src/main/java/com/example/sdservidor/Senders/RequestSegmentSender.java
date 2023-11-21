package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.RequestSegmentData;
import com.example.sdservidor.Senders.Data.RequestUserData;

public class RequestSegmentSender extends BaseSender {
    public RequestSegmentSender(RequestSegmentData data) {
        super(Actions.REQUEST_SEGMENT, data, "Segmento recuperado com sucesso!", false);
    }

    public RequestSegmentSender() {
        super();
    }
}
