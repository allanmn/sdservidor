package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.RemovePointData;
import com.example.sdservidor.Receivers.Data.RemoveUserData;

public class RemovePointReceiver extends BaseReceiver implements IBaseReceiver {

    RemovePointData data;
    public RemovePointReceiver(RemovePointData data) {
        super(Actions.REMOVE_POINT);
        this.data = data;
    }

    public RemovePointReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RemovePointData getData() {
        return data;
    }

    public void setData(RemovePointData data) {
        this.data = data;
    }
}
