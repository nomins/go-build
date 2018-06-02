package com.finedust.nomins.api;

public class FineDustResponse {
    private int code;
    private String reason;
    public void setCode(int errorCode) {
        this.code = errorCode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}
