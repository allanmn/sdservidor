package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.EditUserData;
import com.example.sdservidor.Receivers.Data.SelfEditUserData;

public class SelfEditUserReceiver extends BaseReceiver implements IBaseReceiver {

    SelfEditUserData data;
    public SelfEditUserReceiver(SelfEditUserData data) {
        super(Actions.SELF_EDIT_USER);
        this.data = data;
    }

    public SelfEditUserReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public SelfEditUserData getData() {
        return data;
    }

    public void setData(SelfEditUserData data) {
        this.data = data;
    }
}
