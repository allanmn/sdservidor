package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.ListSegmentsData;
import com.example.sdservidor.Receivers.Data.ListUsersData;

public class ListSegmentsReceiver extends BaseReceiver implements IBaseReceiver {
    ListSegmentsData data;
    public ListSegmentsReceiver(ListSegmentsData data) {
        super(Actions.LIST_SEGMENTS);
        this.data = data;
    }

    public ListSegmentsReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public ListSegmentsData getData() {
        return data;
    }

    public void setData(ListSegmentsData data) {
        this.data = data;
    }
}
