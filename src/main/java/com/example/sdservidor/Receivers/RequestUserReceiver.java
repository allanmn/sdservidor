package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.RemoveUserData;
import com.example.sdservidor.Receivers.Data.RequestUserData;

public class RequestUserReceiver extends BaseReceiver implements IBaseReceiver {

    RequestUserData data;
    public RequestUserReceiver(RequestUserData data) {
        super(Actions.REQUEST_USER);
        this.data = data;
    }

    public RequestUserReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RequestUserData getData() {
        return data;
    }

    public void setData(RequestUserData data) {
        this.data = data;
    }
}
