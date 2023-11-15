package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.CreatePointData;

public class CreatePointReceiver extends BaseReceiver implements IBaseReceiver {

    CreatePointData data;
    public CreatePointReceiver(CreatePointData data) {
        super(Actions.CAD_POINT);
        this.data = data;
    }

    public CreatePointReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public CreatePointData getData() {
        return data;
    }

    public void setData(CreatePointData data) {
        this.data = data;
    }
}
