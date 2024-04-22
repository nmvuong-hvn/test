import model.ForecastDetailModel;
import model.WeatherDetailModel;


public interface ICallBack {
    void getCurrentAndForecast(WeatherDetailModel currentData, ForecastDetailModel foreCastData);
}
