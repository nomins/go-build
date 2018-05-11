package nomins.mongo02;

import org.springframework.data.annotation.Id;

public class FineDust {
    public FineDust(){}
    public FineDust(String date, String cityName, double so2, double co, double o3, double no2, int pm10, int pm25) {
        this.date = date;
        this.cityName = cityName;
        this.so2  = so2;
        this.co   = co;
        this.o3   = o3;
        this.no2  = no2;
        this.pm10 = pm10;
        this.pm25 = pm25;
    }
    @Id
    public String id;
    String date;
    String cityName;
    double so2;
    double co;
    double o3;
    double no2;
    int    pm10;
    int    pm25;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getSo2() {
        return so2;
    }

    public void setSo2(double so2) {
        this.so2 = so2;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public double getO3() {
        return o3;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public double getNo2() {
        return no2;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    public int getPm10() {
        return pm10;
    }

    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    @Override
    public String toString() {
        return "FineDust{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", cityName='" + cityName + '\'' +
                ", so2=" + so2 +
                ", co=" + co +
                ", o3=" + o3 +
                ", no2=" + no2 +
                ", pm10=" + pm10 +
                ", pm25=" + pm25 +
                '}';
    }
}
