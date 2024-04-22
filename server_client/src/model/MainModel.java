package model;


import java.io.Serializable;

public class MainModel implements Serializable {
//    @SerializedName("temp")
    public float temp;
//    @SerializedName("temp_min")

    public float temp_min;
//    @SerializedName("temp_max")

    public float temp_max;
//    @SerializedName("humidity")

    public float humidity;

    public MainModel(float temp, float temp_min, float temp_max, float humidity) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "MainModel{" +
                "temp=" + temp +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", humidity=" + humidity +
                '}';
    }
}
