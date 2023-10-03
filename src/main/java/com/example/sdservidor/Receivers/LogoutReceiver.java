package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.LogoutData;

public class LogoutReceiver extends BaseReceiver implements IBaseReceiver {

    LogoutData data;
    public LogoutReceiver(LogoutData data) {
        super(Actions.LOGOUT);
        this.data = data;
    }

    public LogoutReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public LogoutData getData() {
        return data;
    }

    public void setData(LogoutData data) {
        this.data = data;
    }
}
