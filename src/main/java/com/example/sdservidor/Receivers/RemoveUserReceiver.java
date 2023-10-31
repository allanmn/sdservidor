package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.RemoveUserData;

public class RemoveUserReceiver extends BaseReceiver implements IBaseReceiver {

    RemoveUserData data;
    public RemoveUserReceiver(RemoveUserData data) {
        super(Actions.REMOVE_USER);
        this.data = data;
    }

    public RemoveUserReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RemoveUserData getData() {
        return data;
    }

    public void setData(RemoveUserData data) {
        this.data = data;
    }
}
