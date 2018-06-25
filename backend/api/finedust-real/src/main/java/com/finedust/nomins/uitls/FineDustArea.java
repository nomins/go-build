package com.finedust.nomins.uitls;

import java.util.ArrayList;
import java.util.List;

public enum FineDustArea {
    SEOUL(0, "서울"),
    BUSAN(1, "부산"),
    DAEGU(2, "대구"),
    INCHEON(3, "인천"),
    GWANGJU(4, "광주"),
    DAEJEON(5, "대전"),
    ULSAN(6, "울산"),
    SEJONG(7, "세종"),
    GYEONGGI(8, "경기"),
    GANGWON(9, "강원"),
    CHUNGBUK(10, "충북"),
    CHUNGNAM(11, "충남"),
    JEONBUK(12, "전북"),
    JEONNAM(13, "전남"),
    GYEONGBUK(14, "경북"),
    GYEONGNAM(15, "경남"),
    JEJU(16, "제주");


    private int code;
    private String codeName;

    FineDustArea(int code, String codeName) {
        this.code		= code;
        this.codeName 	= codeName;
    }

    public String getCodeName() {
        return this.codeName;
    }


    public static FineDustArea getEnum(int code) {
        FineDustArea result = null;
        for(FineDustArea fda : FineDustArea.values() )  {
            if(code == fda.code) {
                result = fda;
                break;
            }
        }
        return result;
    }

    public static FineDustArea getEnum(String codeName) {
        FineDustArea result = null;
        for(FineDustArea fda : FineDustArea.values() )  {
            if(fda.codeName.equalsIgnoreCase(codeName)) {
                result = fda;
                break;
            }
        }
        return result;
    }

    public static List<String> getAreaCityNames() {
        List<String> cityNames = new ArrayList<>();
        for(FineDustArea fda : FineDustArea.values() )  {
            cityNames.add(fda.codeName);
        }
        return cityNames;
    }
}