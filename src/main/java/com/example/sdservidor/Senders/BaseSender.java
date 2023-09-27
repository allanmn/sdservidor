package com.example.sdservidor.Senders;

import com.example.sdservidor.Senders.Data.BaseData;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseSender {

    private static final ObjectMapper jackson = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);;

    private String action;

    private String message;

    private boolean error;

    private BaseData data;

    public BaseSender(String action, BaseData data) {
        this.action = action;
        this.data = data;
    }

    public BaseSender(String action, BaseData data, String message) {
        this.action = action;
        this.data = data;
        this.message = message;
    }

    public BaseSender(String action, BaseData data, String message, boolean error) {
        this.action = action;
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public BaseSender(String action) {
        this.action = action;
    }

    public BaseSender() {

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

    public BaseData getData() {
        return data;
    }

    public void setData(BaseData data) {
        this.data = data;
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
