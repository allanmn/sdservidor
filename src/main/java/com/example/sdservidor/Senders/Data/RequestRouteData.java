package com.example.sdservidor.Senders.Data;

import com.example.sdservidor.Models.Segment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RequestRouteData extends BaseData {
    @JsonProperty("segmentos")
    private List<Segment> segments;

    public RequestRouteData(List<Segment> segments) {
        this.segments = segments;
    }

    public RequestRouteData() {}

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}
