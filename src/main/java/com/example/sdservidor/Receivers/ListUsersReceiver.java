package com.example.sdservidor.Receivers;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Receivers.Data.ListUsersData;
import com.example.sdservidor.Receivers.Data.LogoutData;

public class ListUsersReceiver extends BaseReceiver implements IBaseReceiver {

    ListUsersData data;
    public ListUsersReceiver(ListUsersData data) {
        super(Actions.LIST_USERS);
        this.data = data;
    }

    public ListUsersReceiver() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.data.validate();

        return true;
    }

    public ListUsersData getData() {
        return data;
    }

    public void setData(ListUsersData data) {
        this.data = data;
    }
}
