package com.example.sdservidor.Senders.Data;

import com.example.sdservidor.Models.Segment;
import com.example.sdservidor.Models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListSegmentsData extends BaseData {

    @JsonProperty("segmentos")
    private List<Segment> segments;

    public ListSegmentsData(List<Segment> segments) {
        this.segments = segments;
    }

    public ListSegmentsData() {}

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}
