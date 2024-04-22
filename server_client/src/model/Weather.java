package model;


public class Weather {
    public long id ;

    public String main ;

    public String description;
    public String icon;

    public Weather(long id, String main, String description) {
        this.id = id;
        this.main = main;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
