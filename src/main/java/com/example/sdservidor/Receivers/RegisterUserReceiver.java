package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.CreateUserData;
import com.example.sdservidor.Receivers.Data.RegisterUserData;

public class RegisterUserReceiver extends BaseReceiver implements IBaseReceiver {

    RegisterUserData data;
    public RegisterUserReceiver(RegisterUserData data) {
        super(Actions.REGISTER_USER);
        this.data = data;
    }

    public RegisterUserReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RegisterUserData getData() {
        return data;
    }

    public void setData(RegisterUserData data) {
        this.data = data;
    }
}
