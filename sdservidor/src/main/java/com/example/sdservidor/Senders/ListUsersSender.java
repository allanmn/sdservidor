package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.ListUsersData;

public class ListUsersSender extends BaseSender {
    public ListUsersSender(ListUsersData data) {
        super(Actions.LIST_USERS, data, "Usuários recuperados com sucesso!", false);
    }

    public ListUsersSender() {
        super();
    }
}
