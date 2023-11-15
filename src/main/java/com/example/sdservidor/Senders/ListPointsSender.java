package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.ListPointsData;
import com.example.sdservidor.Senders.Data.ListUsersData;

public class ListPointsSender extends BaseSender {
    public ListPointsSender(ListPointsData data) {
        super(Actions.LIST_POINTS, data, "Pontos recuperados com sucesso!", false);
    }

    public ListPointsSender() {
        super();
    }
}
