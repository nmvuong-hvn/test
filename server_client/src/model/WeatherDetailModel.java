package model;


import java.io.Serializable;
import java.util.List;

public class WeatherDetailModel implements Serializable {

//    @SerializedName("weather")
    public List<Weather> weather;
//    @SerializedName("main")
    public MainModel main;
//    @SerializedName("dt_txt")
    public String dt_txt;
//    @SerializedName("wind")
    public WindModel windModel;

    public WeatherDetailModel() {
    }

    public WeatherDetailModel(List<Weather> weather, MainModel main, String dt_txt, WindModel windModel) {
        this.weather = weather;
        this.main = main;
        this.dt_txt = dt_txt;
        this.windModel = windModel;
    }

    @Override
    public String toString() {
        return "WeatherDetailModel{" +
                "weather=" + weather +
                ", main=" + main +
                ", dt_txt='" + dt_txt + '\'' +
                ", windModel=" + windModel +
                '}';
    }
}
