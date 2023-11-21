package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.RemoveSegmentData;
import com.example.sdservidor.Receivers.Data.RemoveUserData;

public class RemoveSegmentReceiver extends BaseReceiver implements IBaseReceiver {

    RemoveSegmentData data;
    public RemoveSegmentReceiver(RemoveSegmentData data) {
        super(Actions.REMOVE_SEGMENT);
        this.data = data;
    }

    public RemoveSegmentReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RemoveSegmentData getData() {
        return data;
    }

    public void setData(RemoveSegmentData data) {
        this.data = data;
    }
}
