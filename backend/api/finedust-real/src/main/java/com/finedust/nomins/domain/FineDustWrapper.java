package com.finedust.nomins.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FineDustWrapper {
    private final FineDust fineDust;
    private final String   level;
    private String dataTime;
    private String province;
    private double figure;
    private String status;

    public FineDustWrapper(FineDust fineDust, String level) {
        this.fineDust = fineDust;
        this.level    = level;
    }

    public String getDataTime() {
        String utcDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        TimeZone tz = TimeZone.getDefault();
        try{
            Date parseDate = sdf.parse(fineDust.date);
            long milliseconds = parseDate.getTime();
            int offset = tz.getOffset(milliseconds);

            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

            utcDate = utcFormat.format(milliseconds - offset);
            utcDate = utcDate.replace("+0000", "");
        }catch(Exception e){
            e.printStackTrace();
        }
        return utcDate;
    }

    public String getProvince() {
        return fineDust.region;
    }

    public double getFigure() {
        if(level.equalsIgnoreCase("detail")){
            return fineDust.pm25;
        }else{
            return fineDust.pm10;
        }
    }

    public String getStatus() {
        double figure = getFigure();

        if(level.equalsIgnoreCase("detail")){
            return pm25Standard(figure);
        }else{
            return pm10Standard(figure);
        }
    }

    public String pm25Standard(double figure) {
        if(figure <= 15){
            return "good";
        }
        else if(figure <= 25){
            return "normal";
        }
        else if(figure <= 50){
            return "bad";
        }
        else{
            return "worst";
        }
    }


    public String pm10Standard(double figure) {
        if(figure <= 30){
            return "good";
        }
        else if(figure <= 50){
            return "normal";
        }
        else if(figure <= 100){
            return "bad";
        }
        else{
            return "worst";
        }
    }
}
