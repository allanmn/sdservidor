package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.CreateUserData;
import com.example.sdservidor.Receivers.Data.EditUserData;

public class EditUserReceiver extends BaseReceiver implements IBaseReceiver {

    EditUserData data;
    public EditUserReceiver(EditUserData data) {
        super(Actions.EDIT_USER);
        this.data = data;
    }

    public EditUserReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public EditUserData getData() {
        return data;
    }

    public void setData(EditUserData data) {
        this.data = data;
    }
}
