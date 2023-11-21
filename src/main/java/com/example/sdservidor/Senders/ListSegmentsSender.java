package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Senders.Data.ListSegmentsData;
import com.example.sdservidor.Senders.Data.ListUsersData;

public class ListSegmentsSender extends BaseSender {
    public ListSegmentsSender(ListSegmentsData data) {
        super(Actions.LIST_SEGMENTS, data, "Segmentos recuperados com sucesso!", false);
    }

    public ListSegmentsSender() {
        super();
    }
}
