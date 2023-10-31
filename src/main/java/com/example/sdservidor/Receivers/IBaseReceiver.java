package com.example.sdservidor.Receivers;

import com.example.sdservidor.Exceptions.ValidationException;

public interface IBaseReceiver {
    public boolean validate() throws ValidationException;

}
