package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.LoginData;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LoginReceiver extends BaseReceiver implements IBaseReceiver {

    LoginData data;
    public LoginReceiver(LoginData data) {
        super(Actions.LOGIN);
        this.data = data;
    }

    public LoginReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
