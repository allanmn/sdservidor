package com.example.sdservidor.Senders;

import com.example.sdservidor.Actions.Actions;
import com.example.sdservidor.Models.RouteSegment;
import com.example.sdservidor.Models.Segment;
import com.example.sdservidor.Senders.Data.BaseData;
import com.example.sdservidor.Senders.Data.RequestRouteData;
import com.example.sdservidor.Senders.Data.RequestSegmentData;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RequestRouteSender extends BaseSender {

    private static final ObjectMapper jackson = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);;

    private String action;

    private String message;

    private boolean error;
    @JsonProperty("segmentos")
    private List<RouteSegment> segments;

    public RequestRouteSender(List<RouteSegment> segments) {
        this.action = Actions.REQUEST_ROUTE;
        this.message = "Rota recuperada com sucesso!";
        this.error = false;
        this.segments = segments;
    }

    public String toJson() throws JsonProcessingException {
        return jackson.writeValueAsString(this);
    }
    public static <T> T fromJson(String json, Class<T> generic_response) throws JsonProcessingException {
        return jackson.readValue(json, generic_response);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean getError() {
        return this.error;
    }
}
