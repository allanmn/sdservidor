package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.RequestAutoUserData;
import com.example.sdservidor.Receivers.Data.RequestUserData;

public class RequestAutoUserReceiver extends BaseReceiver implements IBaseReceiver {

    RequestAutoUserData data;
    public RequestAutoUserReceiver(RequestAutoUserData data) {
        super(Actions.REQUEST_AUTO_USER);
        this.data = data;
    }

    public RequestAutoUserReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RequestAutoUserData getData() {
        return data;
    }

    public void setData(RequestAutoUserData data) {
        this.data = data;
    }
}
