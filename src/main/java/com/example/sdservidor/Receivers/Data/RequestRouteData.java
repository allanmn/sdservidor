package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.example.sdservidor.Models.Point;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestRouteData extends BaseData {

    @JsonProperty("ponto_origem")
    Point pontoOrigem;

    @JsonProperty("ponto_destino")
    Point pontoDestino;

    public RequestRouteData(long segmentId) {
    }

    public RequestRouteData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public Point getPontoOrigem() {
        return pontoOrigem;
    }

    public Point getPontoDestino() {
        return pontoDestino;
    }

    public void setPontoOrigem(Point pontoOrigem) {
        this.pontoOrigem = pontoOrigem;
    }

    public void setPontoDestino(Point pontoDestino) {
        this.pontoDestino = pontoDestino;
    }
}
