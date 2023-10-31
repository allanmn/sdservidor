package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.CreateUserData;

public class CreateUserReceiver extends BaseReceiver implements IBaseReceiver {

    CreateUserData data;
    public CreateUserReceiver(CreateUserData data) {
        super(Actions.CAD_USER);
        this.data = data;
    }

    public CreateUserReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public CreateUserData getData() {
        return data;
    }

    public void setData(CreateUserData data) {
        this.data = data;
    }
}
