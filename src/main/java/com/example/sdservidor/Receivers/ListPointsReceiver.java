package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.ListPointsData;
import com.example.sdservidor.Receivers.Data.ListUsersData;

public class ListPointsReceiver extends BaseReceiver implements IBaseReceiver {
    ListPointsData data;
    public ListPointsReceiver(ListPointsData data) {
        super(Actions.LIST_POINTS);
        this.data = data;
    }

    public ListPointsReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public ListPointsData getData() {
        return data;
    }

    public void setData(ListPointsData data) {
        this.data = data;
    }
}
