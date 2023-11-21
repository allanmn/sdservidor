package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.EditSegmentData;
import com.example.sdservidor.Receivers.Data.EditUserData;

public class EditSegmentReceiver extends BaseReceiver implements IBaseReceiver {

    EditSegmentData data;
    public EditSegmentReceiver(EditSegmentData data) {
        super(Actions.EDIT_SEGMENT);
        this.data = data;
    }

    public EditSegmentReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public EditSegmentData getData() {
        return data;
    }

    public void setData(EditSegmentData data) {
        this.data = data;
    }
}
