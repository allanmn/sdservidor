package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.CreateSegmentData;
import com.example.sdservidor.Receivers.Data.CreateUserData;

public class CreateSegmentReceiver extends BaseReceiver implements IBaseReceiver {

    CreateSegmentData data;
    public CreateSegmentReceiver(CreateSegmentData data) {
        super(Actions.CAD_SEGMENT);
        this.data = data;
    }

    public CreateSegmentReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public CreateSegmentData getData() {
        return data;
    }

    public void setData(CreateSegmentData data) {
        this.data = data;
    }
}
