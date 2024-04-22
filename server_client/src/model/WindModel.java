package model;


public class WindModel {

//    @SerializedName("speed")
    public float speed;

//    @SerializedName("deg")
    public float deg;

//    @SerializedName("gust")
    public float gust;

    public WindModel() {
    }

    public WindModel(float speed, float deg, float gust) {
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }

    @Override
    public String toString() {
        return "WindModel{" +
                "speed=" + speed +
                ", deg=" + deg +
                ", gust=" + gust +
                '}';
    }
}
