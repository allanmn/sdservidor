package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.RequestPointData;
import com.example.sdservidor.Receivers.Data.RequestUserData;

public class RequestPointReceiver extends BaseReceiver implements IBaseReceiver {

    RequestPointData data;
    public RequestPointReceiver(RequestPointData data) {
        super(Actions.REQUEST_POINT);
        this.data = data;
    }

    public RequestPointReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RequestPointData getData() {
        return data;
    }

    public void setData(RequestPointData data) {
        this.data = data;
    }
}
