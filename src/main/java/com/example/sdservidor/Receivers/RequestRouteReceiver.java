package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.RequestRouteData;
import com.example.sdservidor.Receivers.Data.RequestSegmentData;

public class RequestRouteReceiver extends BaseReceiver implements IBaseReceiver {

    RequestRouteData data;
    public RequestRouteReceiver(RequestRouteData data) {
        super(Actions.REQUEST_ROUTE);
        this.data = data;
    }

    public RequestRouteReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RequestRouteData getData() {
        return data;
    }

    public void setData(RequestRouteData data) {
        this.data = data;
    }
}
