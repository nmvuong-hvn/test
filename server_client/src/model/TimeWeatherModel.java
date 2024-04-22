package model;

public class TimeWeatherModel {

   public String time ;
   public Float  temperature ;
   public String url ;

    public TimeWeatherModel() {
    }

    public TimeWeatherModel(String time, Float temperature, String url) {
        this.time = time;
        this.temperature = temperature;
        this.url =url;
    }
}
