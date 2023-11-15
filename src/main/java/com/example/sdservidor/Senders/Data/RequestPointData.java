package com.example.sdservidor.Senders.Data;

import com.example.sdservidor.Models.Point;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestPointData extends BaseData {

    @JsonProperty("ponto")
    private Point point;

    public RequestPointData(Point point) {
        this.point = point;
    }

    public RequestPointData() {}

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
