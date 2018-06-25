package com.finedust.nomins.Api;

import com.finedust.nomins.domain.FineDustWrapper;

public class FineDustResponse {
    int code;
    String errorReason;
    //FineDust fineDust;
    FineDustWrapper fineDust;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public FineDustWrapper getFineDust() {
        return fineDust;
    }

    public void setFineDust(FineDustWrapper fineDust) {
        this.fineDust = fineDust;
    }

    @Override
    public String toString() {
        return "FineDustResponse{" +
                "code=" + code +
                ", errorReason='" + errorReason + '\'' +
                ", fineDust=" + fineDust +
                '}';
    }
}
