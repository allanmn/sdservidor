package com.example.sdservidor.Senders.Data;

import com.example.sdservidor.Models.Point;
import com.example.sdservidor.Models.User;

import java.util.List;

public class ListPointsData extends BaseData {
    private List<Point> pontos;

    public ListPointsData(List<Point> points) {
        this.pontos = points;
    }

    public ListPointsData() {}

    public List<Point> getPontos() {
        return pontos;
    }

    public void setPontos(List<Point> points) {
        this.pontos = points;
    }
}
