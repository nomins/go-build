package com.finedust.nomins.Api;

public class ApiRequestException extends Exception{

    public ApiRequestException(){}

    public  ApiRequestException(String errorMsg) {
        super(errorMsg);
    }
}
