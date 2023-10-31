package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.LoginData;
import com.example.sdservidor.Receivers.Data.RemoveSelfUserData;

public class RemoveSelfUserReceiver extends BaseReceiver implements IBaseReceiver {

    RemoveSelfUserData data;
    public RemoveSelfUserReceiver(RemoveSelfUserData data) {
        super(Actions.REMOVE_SELF_USER);
        this.data = data;
    }

    public RemoveSelfUserReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public RemoveSelfUserData getData() {
        return data;
    }

    public void setData(RemoveSelfUserData data) {
        this.data = data;
    }
}
