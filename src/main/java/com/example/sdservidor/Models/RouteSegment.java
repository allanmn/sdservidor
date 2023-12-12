package com.example.sdservidor.Models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

public class RouteSegment {

    private Point ponto_origem;
    private Point ponto_destino;

    private String direcao;

    private int distancia;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String obs;

    public RouteSegment(Point ponto_destino, Point ponto_origem, String direcao, int distancia, String obs) {
        this.ponto_destino = ponto_destino;
        this.ponto_origem = ponto_origem;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs;
    }

    public RouteSegment() {}

    public String getDirecao() {
        return direcao;
    }

    public int getDistancia() {
        return distancia;
    }

    public String getObs() {
        return obs;
    }

    public Point getPonto_destino() {
        return ponto_destino;
    }

    public Point getPonto_origem() {
        return ponto_origem;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setPonto_destino(Point ponto_destino) {
        this.ponto_destino = ponto_destino;
    }

    public void setPonto_origem(Point ponto_origem) {
        this.ponto_origem = ponto_origem;
    }
}
