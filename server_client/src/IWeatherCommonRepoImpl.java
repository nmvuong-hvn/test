import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IWeatherCommonRepoImpl implements IWeatherCommonRepository {
    WeatherDetailModel currData;
    ForecastDetailModel forecastData;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    ICallBack iCallBack;

    public IWeatherCommonRepoImpl(ICallBack iCallBack) {
        this.iCallBack = iCallBack;
    }


    @Override
    public List<Weather> getFiveDaysWeather(String query) {
        executor.execute(() -> {
            try {
                if (!query.isEmpty()) {

                    // read current data v
                    String currStr = getData("http://api.openweathermap.org/data/2.5/weather?q=" + query + "&APPID=3506dfa8bbebf7709e6fba904a68559a&units=metric");
                    System.out.println("currStr = "+currStr);
                    JSONObject jsonObject = new JSONObject(currStr);
                    currData = covertToObject(jsonObject);
                    System.out.println("CurrentData = "+ currData);
                    // read forecast data
                    String forecastStr = getData("http://api.openweathermap.org/data/2.5/forecast?q=" + query + "&APPID=3506dfa8bbebf7709e6fba904a68559a&units=metric");
                    forecastData = convertToObject(forecastStr);
                    System.out.println("forecastData = "+ forecastData);

                }
                if (iCallBack != null) iCallBack.getCurrentAndForecast(currData, forecastData);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        return null;
    }
    public WeatherDetailModel covertToObject(JSONObject jsonObject) {

        JSONArray weatherArray = jsonObject.getJSONArray("weather");
        JSONObject mainObject = jsonObject.getJSONObject("main");
        JSONObject windObject = jsonObject.getJSONObject("wind");
        String dateTime = jsonObject.optString("dt_txt");
        WeatherDetailModel weatherDetailModel = new WeatherDetailModel();
        if (weatherArray != null) {
            List<Weather> dataWeather = new ArrayList<>();
            dataWeather.add(convertToWeatherModel(weatherArray));
            weatherDetailModel.weather = dataWeather;
        }
        if (mainObject != null){
            weatherDetailModel.main = convertToMainModel(mainObject);
        }
        if (windObject != null){
            weatherDetailModel.windModel = convertToWindModel(windObject);
        }
        if (dateTime != null ) {
            weatherDetailModel.dt_txt = dateTime;
        }
        return weatherDetailModel;
    }
    public ForecastDetailModel convertToObject(String data){
        JSONObject jsonObject = new JSONObject(data);
        JSONArray weatherDetailArray = jsonObject.getJSONArray("list");
        int cod = jsonObject.getInt("cod");
        int cnt = jsonObject.getInt("cnt");
        ForecastDetailModel forecastDetailModel = new ForecastDetailModel();
        forecastDetailModel.cod = cod;
        forecastDetailModel.cnt = cnt;
        if (weatherDetailArray != null){
            List<WeatherDetailModel> list = new ArrayList<>();
            for (int i = 0 ; i < weatherDetailArray.length() ;i++){
                JSONObject weatherJsonObject = weatherDetailArray.getJSONObject(i);
                WeatherDetailModel weatherDetailModel = covertToObject(weatherJsonObject);
                list.add(weatherDetailModel);
            }
            forecastDetailModel.list = list;
        }
        return forecastDetailModel;
    }



    public WindModel convertToWindModel(JSONObject jsonObject){
        float speed = jsonObject.getFloat("speed");
        float deg = jsonObject.getFloat("deg");
        float gust = jsonObject.getFloat("gust");

        return new WindModel(speed,deg,gust);
    }

    public MainModel convertToMainModel(JSONObject jsonObject){
        float temp = jsonObject.getFloat("temp");
        float temp_min = jsonObject.getFloat("temp_min");
        float temp_max = jsonObject.getFloat("temp_max");
        float humidity = jsonObject.getFloat("humidity");

        return new MainModel(temp,temp_min,temp_max,humidity);
    }

    public Weather convertToWeatherModel(JSONArray weatherArray) {
        JSONObject weather = weatherArray.getJSONObject(0);
        long id = weather.getLong("id");
        String main = weather.getString("main");
        String description = weather.getString("description");

        return new Weather(id, main, description);
    }

    public String getData(String urlstr) throws IOException {
        URL url = new URL(urlstr);
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        String data = "";
        while ((inputLine = in.readLine()) != null)
            data += inputLine;
        in.close();
        return data;
    }
}
