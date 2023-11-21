package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Helpers.Validators;
import com.example.sdservidor.Models.Segment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditSegmentData extends BaseData {
    private String token;

    @JsonProperty("segmento_id")
    long segmentId;

    @JsonProperty("segmento")
    Segment segment;

    public EditSegmentData(long segmentId, Segment segment, String token) {
        this.token = token;
        this.segment = segment;
        this.segmentId = segmentId;
    }

    public EditSegmentData() {}

    public boolean validate() throws ValidationException {
        if (segment == null) {
            throw  new ValidationException("Segmento é obrigatório");
        }

        if (segment.getPonto_destino() == null) {
            throw  new ValidationException("Ponto de destino é obrigatório");
        }

        if (segment.getPonto_origem() == null) {
            throw  new ValidationException("Ponto de origem é obrigatório");
        }

        if (segment.getDirecao() == null || segment.getDirecao().isEmpty()) {
            throw  new ValidationException("Direção é obrigatório");
        }

        if (segment.getDistancia() == null || segment.getDistancia().isEmpty()) {
            throw  new ValidationException("Distância é obrigatório");
        }

        if (segmentId == 0) {
            throw  new ValidationException("ID do segmento é obrigatório");
        }

        return true;
    }

    public Segment getSegment() {
        return segment;
    }

    public long getSegmentId() {
        return segmentId;
    }

    public String getToken() {
        return token;
    }

    public void setSegmentId(long segmentId) {
        this.segmentId = segmentId;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
