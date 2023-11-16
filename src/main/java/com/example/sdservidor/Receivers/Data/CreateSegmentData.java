package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Helpers.Validators;
import com.example.sdservidor.Models.Segment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateSegmentData extends BaseData {
    private String token;

    private Segment segmento;

    public CreateSegmentData(Segment segment, String token) {
        this.token = token;
        this.segmento = segment;
    }

    public CreateSegmentData() {}

    public boolean validate() throws ValidationException {
        if (segmento == null) {
            throw  new ValidationException("Segmento é obrigatório");
        }

        if (segmento.getPonto_origem() == null) {
            throw  new ValidationException("Ponto de origem é obrigatório");
        }

        if (segmento.getPonto_destino() == null) {
            throw  new ValidationException("Ponto de destino é obrigatório");
        }

        if (segmento.getDirecao() == null || segmento.getDirecao().isEmpty()) {
            throw  new ValidationException("Direção é obrigatório");
        }

        if (segmento.getDistancia() == null || segmento.getDistancia().isEmpty()) {
            throw  new ValidationException("Distância é obrigatório");
        }

        return true;
    }

    public Segment getSegmento() {
        return segmento;
    }

    public void setSegmento(Segment segmento) {
        this.segmento = segmento;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
