package com.example.sdservidor.Senders;

public class ErrorSender extends BaseSender {
    public ErrorSender(String action, String message) {
        super(action);
        this.setError(true);
        this.setMessage(message);
    }

    public ErrorSender() {
        super();
    }
}
