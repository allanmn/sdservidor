package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.RequestSegmentData;
import com.example.sdservidor.Receivers.Data.RequestUserData;

public class RequestSegmentReceiver extends BaseReceiver implements IBaseReceiver {

    RequestSegmentData data;
    public RequestSegmentReceiver(RequestSegmentData data) {
        super(Actions.REQUEST_SEGMENT);
        this.data = data;
    }

    public RequestSegmentReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RequestSegmentData getData() {
        return data;
    }

    public void setData(RequestSegmentData data) {
        this.data = data;
    }
}
