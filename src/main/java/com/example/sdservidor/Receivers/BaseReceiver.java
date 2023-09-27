package com.example.sdservidor.Receivers;

import com.example.sdservidor.Receivers.Data.BaseData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseReceiver {

    private static final ObjectMapper jackson = new ObjectMapper();

    private String action;

    public BaseReceiver(String action) {
        this.action = action;
    }

    public BaseReceiver() {

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
}
