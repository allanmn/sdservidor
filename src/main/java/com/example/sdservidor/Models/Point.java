package com.example.sdservidor.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pontos")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "obs")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String obs;

    @JsonIgnore
    @OneToMany(mappedBy = "ponto_origem", cascade = CascadeType.ALL)
    private List<Segment> segmentosOrigem;

    @JsonIgnore
    @OneToMany(mappedBy = "ponto_destino", cascade = CascadeType.ALL)
    private List<Segment> segmentosDestino;

    public Point(String nome, String obs) {
        this.name = nome;
        this.obs = obs;
    }

    public Point() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
