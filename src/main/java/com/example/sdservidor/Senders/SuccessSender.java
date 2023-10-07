package com.example.sdservidor.Senders;

public class SuccessSender extends BaseSender {
    public SuccessSender(String action, String message) {
        super(action);
        this.setError(false);
        this.setMessage(message);
    }

    public SuccessSender() {
        super();
    }
}
