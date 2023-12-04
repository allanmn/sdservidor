package com.example.sdservidor.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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
    private int distancia;

    @Column(name = "bloqueado")
    private boolean bloqueado;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Column(name = "obs")
    private String obs;

    public Segment (Point ponto_destino, Point ponto_origem, String direcao, int distancia, String obs, boolean bloqueado) {
        this.ponto_destino = ponto_destino;
        this.ponto_origem = ponto_origem;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs;
        this.bloqueado = bloqueado;
    }

    public Segment() {}

    public String getDirecao() {
        return direcao;
    }

    public int getDistancia() {
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

    public void setDistancia(int distancia) {
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

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean getBloqueado() {
        return this.bloqueado;
    }
}
