package com.example.sdservidor.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "segmentos")
public class Segment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "ponto_origem_id")
    private Point ponto_origem;
    @ManyToOne()
    @JoinColumn(name = "ponto_destino_id")
    private Point ponto_destino;

    @Column(name = "direcao")
    private String direcao;

    @Column(name = "distancia")
    private String distancia;

    @Column(name = "obs")
    private String obs;

    public Segment (Point ponto_destino, Point ponto_origem, String direcao, String distancia, String obs) {
        this.ponto_destino = ponto_destino;
        this.ponto_origem = ponto_origem;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs;
    }

    public Segment() {}

    public String getDirecao() {
        return direcao;
    }

    public String getDistancia() {
        return distancia;
    }

    public long getId() {
        return id;
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

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public void setId(long id) {
        this.id = id;
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
