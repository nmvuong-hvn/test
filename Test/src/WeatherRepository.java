import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public interface WeatherRepository {
    List<Object> get5DaysWeather() ;
    String getCurrentTime();

}

class WeatherRepositoryImpl implements WeatherRepository {

    @Override
    public List<Object> get5DaysWeather() {
        
        return null;
    }

    @Override
    public String getCurrentTime() {
        return null;
    }
}