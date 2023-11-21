package com.example.sdservidor.Senders.Data;

import com.example.sdservidor.Models.Segment;
import com.example.sdservidor.Models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestSegmentData extends BaseData {
    @JsonProperty("segmento")
    private Segment segment;

    public RequestSegmentData(Segment segment) {
        this.segment = segment;
    }

    public RequestSegmentData() {}

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }
}
