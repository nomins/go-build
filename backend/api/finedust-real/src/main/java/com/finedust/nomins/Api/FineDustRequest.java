package com.finedust.nomins.Api;

public class FineDustRequest {

    String type;
    String province;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    @Override
    public String toString() {
        return "FineDustRequest{" +
                "type='" + type + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
