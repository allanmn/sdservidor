package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.EditPointData;
import com.example.sdservidor.Receivers.Data.EditUserData;

public class EditPointReceiver extends BaseReceiver implements IBaseReceiver {

    EditPointData data;
    public EditPointReceiver(EditPointData data) {
        super(Actions.EDIT_POINT);
        this.data = data;
    }

    public EditPointReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public EditPointData getData() {
        return data;
    }

    public void setData(EditPointData data) {
        this.data = data;
    }
}
