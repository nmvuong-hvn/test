import model.Weather;

import java.util.List;

public interface IWeatherCommonRepository {
    List<Weather> getFiveDaysWeather(String query);
}
