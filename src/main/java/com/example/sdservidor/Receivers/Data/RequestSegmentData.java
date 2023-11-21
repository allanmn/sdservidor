package com.example.sdservidor.Receivers.Data;

import com.example.sdservidor.Exceptions.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestSegmentData extends BaseData {
    private String token;

    @JsonProperty("segmento_id")
    private long segmentId;

    public RequestSegmentData(String token, long segmentId) {
        this.token = token;
        this.segmentId = segmentId;
    }

    public RequestSegmentData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(long segmentId) {
        this.segmentId = segmentId;
    }
}
