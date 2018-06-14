package com.finedust.nomins.api;

public class ApiRequestException extends Exception{

    public ApiRequestException(){
        super();
    }

    public ApiRequestException(String errorMessage) {
        super(errorMessage);
    }

}
