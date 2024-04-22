package model;


import java.util.List;

public class ForecastDetailModel {
//    @SerializedName("list")?
    public List<WeatherDetailModel> list ;
//    @SerializedName("cod")
    public int cod ;
//    @SerializedName("cnt")
    public int cnt ;

    public ForecastDetailModel(List<WeatherDetailModel> list, int cod, int cnt) {
        this.list = list;
        this.cod = cod;
        this.cnt = cnt;
    }

    public ForecastDetailModel() {
    }

    @Override
    public String toString() {
        return "ForecastDetailModel{" +
                "list=" + list +
                ", cod=" + cod +
                ", cnt=" + cnt +
                '}';
    }
}
