import model.ForecastDetailModel;
import model.TimeWeatherModel;
import model.WeatherDetailModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WeatherServer extends JFrame implements Runnable,ICallBack {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JLabel lb_location;
    private JPanel lb_temperature;
    private JLabel lb_time_now;
    private JPanel img_weather_1;
    private JPanel lb_temp_1;
    private JPanel lb_day_1;
    private JPanel lb_weather_2;
    private JPanel lb_temp_2;
    private JPanel lb_;
    private JLabel lb_img_weather_1;
    private JLabel lb_temper_1;
    private JLabel lb_days_1;
    private JLabel lb_img_weather_2;
    private JLabel lb_temper_2;
    private JLabel lb_days_2;
    private JLabel lb_img_weather_3;
    private JLabel lb_temper_3;
    private JLabel lb_days_3;
    private JLabel lb_img_weather_4;
    private JLabel lb_temper_4;
    private JLabel lb_days_4;
    private JLabel lb_img_weather_5;
    private JLabel lb_temper_5;
    private JLabel lb_days_5;
    private JLabel lb_temper;
    private JLabel lb_max_min_weather;
    private JLabel lb_humidity;
    private JLabel lb_wind_value;
    private JLabel lb_img_current;
    private JPanel panel_Bg;
    private JComboBox cb_options_national;
    private JLabel lb_value_time;
    private JLabel lb_national_1;
    private JLabel lb_time_value_1;
    private JLabel lb_national_2;
    private JLabel lb_time_value_3;
    private JLabel lb_national_3;
    private JLabel lb_time_value_4;
    private JLabel lb_national_4;
    private JLabel lb_time_value_5;
    private JLabel lb_national_5;
    String s1[] = {"Ha Noi","Hai Phong","Hue","Ho Chi Minh","Ha Giang"};
    String [] timeNational = {"Asia/Ho_Chi_Minh", "America/New_York", "Asia/Singapore", "Asia/Manila","Asia/Jakarta"};

    private boolean flag = true ;
    IWeatherCommonRepoImpl iWeatherCommonRepo;
    private final Thread mThread ;

    private final Thread mThreadTime ;

    AtomicInteger duplicatedClick = new AtomicInteger();

    public WeatherServer(){
        System.out.println("Data");

        setTitle("Server - Dự báo thời tiết");
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tabbedPane1.setBackgroundAt(1, Color.GREEN); //Whatever color

        setSize(700,500);
        setLocationRelativeTo(null);
        setVisible(true);
        lb_temper.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_value_time.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_national_1.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_time_value_1.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_national_2.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_time_value_3.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_national_3.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_time_value_4.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_national_4.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_time_value_5.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30
        lb_national_5.setFont(new Font("Arial", Font.BOLD, 35)); //Creating an Arial Font Style with size 30

        DefaultComboBoxModel model = new DefaultComboBoxModel(s1);
        cb_options_national.setModel(model);

        cb_options_national.addItemListener(e -> {
            int data = cb_options_national.getSelectedIndex();
            if (duplicatedClick.get() == 0 ) {
                duplicatedClick.set(1);
                System.out.println(s1[data]);
                lb_location.setText(s1[data]);
                if (data == 1) {
                    String []city = s1[data].split(" ");
                    iWeatherCommonRepo.getFiveDaysWeather(city[0]+city[1]);

                }else {

                    iWeatherCommonRepo.getFiveDaysWeather(s1[data]);
                }

            }
        });

        mThread = new Thread(this);
        mThreadTime = new Thread(() -> {
            while (true){
                String vietNam = getCurrentTime(timeNational[0]);
                String usa = getCurrentTime(timeNational[1]);
                String singapore = getCurrentTime(timeNational[2]);
                String philip = getCurrentTime(timeNational[3]);
                String indo = getCurrentTime(timeNational[4]);

                lb_value_time.setText(vietNam);
                lb_national_1.setText("Viet Nam");
                lb_time_value_1.setText(usa);
                lb_national_2.setText("American");
                lb_time_value_3.setText(singapore);
                lb_national_3.setText("Singapore");

                lb_time_value_4.setText(philip);
                lb_national_4.setText("Philippines");
                lb_time_value_5.setText(indo);
                lb_national_5.setText("Indonesia");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        mThreadTime.start();
        lb_location.setText("Ha Noi");
        iWeatherCommonRepo = new IWeatherCommonRepoImpl(this);
        iWeatherCommonRepo.getFiveDaysWeather(s1[0]);

//        lb_time_now.setText(getCurrentTime());
        mThread.start();

    }

    @Override
    public void run() {
        while (flag){
//            System.out.println(iWeatherRepository.getCurrentTime());
//            lb_time_now.setText(iWeatherRepository.getCurrentTime());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void getCurrentAndForecast(WeatherDetailModel currentData, ForecastDetailModel foreCastData) {
        duplicatedClick.set(0);
        lb_temper.setText(currentData.main.temp+" \u00B0" + "C");
        System.out.println(currentData.main.temp_max + " | " + currentData.main.temp_min);
        lb_max_min_weather.setText("max. "+currentData.main.temp_max+"\u00B0" + "C" + ", min. " + currentData.main.temp_min+"\u00B0" + "C");
        Calendar calendar = Calendar.getInstance();
        List<TimeWeatherModel> timeWeatherModelList = new ArrayList<>();
        for (int i = 7 ; i < foreCastData.list.size() ; i += 8) {
            String time = foreCastData.list.get(i).dt_txt ;
            Float temp = foreCastData.list.get(i).main.temp;
            String url = foreCastData.list.get(i).weather.get(0).main;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDate date = LocalDate.parse(time,formatter);
            timeWeatherModelList.add(new TimeWeatherModel(date.getDayOfWeek().name(), temp, url));
        }

        lb_img_current.setIcon(new ImageIcon(getUrlImage(currentData.weather.get(0).main)));
        lb_humidity.setText("Humidity: "+currentData.main.humidity+ "%");
        lb_wind_value.setText("Wind: "+ currentData.windModel.speed +"m/s");
        URL urlImg1 = getUrlImage(timeWeatherModelList.get(0).url);
        URL urlImg2 = getUrlImage(timeWeatherModelList.get(1).url);
        URL urlImg3 = getUrlImage(timeWeatherModelList.get(2).url);
        URL urlImg4 = getUrlImage(timeWeatherModelList.get(3).url);
        URL urlImg5 = getUrlImage(timeWeatherModelList.get(4).url);

        System.out.println("urlImg1 = " + urlImg1);
        lb_img_weather_1.setIcon(new ImageIcon(urlImg1));
        lb_img_weather_2.setIcon(new ImageIcon(urlImg2));
        lb_img_weather_3.setIcon(new ImageIcon(urlImg3));
        lb_img_weather_4.setIcon(new ImageIcon(urlImg4));
        lb_img_weather_5.setIcon(new ImageIcon(urlImg5));

        lb_temper_1.setText(timeWeatherModelList.get(0).temperature +"\u00B0" + "C");
        lb_days_1.setText(timeWeatherModelList.get(0).time + " ");
        lb_temper_2.setText(timeWeatherModelList.get(1).temperature +"\u00B0" + "C");
        lb_days_2.setText(timeWeatherModelList.get(1).time + " ");
        lb_temper_3.setText(timeWeatherModelList.get(2).temperature +"\u00B0" + "C");
        lb_days_3.setText(timeWeatherModelList.get(2).time + " ");
        lb_temper_4.setText(timeWeatherModelList.get(3).temperature +"\u00B0" + "C");
        lb_days_4.setText(timeWeatherModelList.get(3).time + " ");
        lb_temper_5.setText(timeWeatherModelList.get(4).temperature +"\u00B0" + "C");
        lb_days_5.setText(timeWeatherModelList.get(4).time + " ");
    }

    URL getUrlImage(String url){
        System.out.println("urlCheck = " + url.toLowerCase(Locale.ROOT));
        System.out.println(this.getClass().getResource("/images/rain_64.png"));
        if (url.toLowerCase(Locale.ROOT).contains("rain")){
          return this.getClass().getResource("/images/rain_64.png");
        }else if (url.toLowerCase(Locale.ROOT).contains("cloud")){
            return this.getClass().getResource("/images/cloud_64.png");
        }else if (url.toLowerCase(Locale.ROOT).contains("snow")){
            return this.getClass().getResource("/images/snowflake_64.png");

        }else if (url.toLowerCase(Locale.ROOT).contains("thunderstorm")){
            return this.getClass().getResource("/images/thunderstorm_64.png");
        }else{
            return this.getClass().getResource("/images/clear_sky_64.png");
        }
    }

    public String getCurrentTime(String national){
        SimpleDateFormat sdfAmerica = new SimpleDateFormat("HH:mm:ss a");
        sdfAmerica.setTimeZone(TimeZone.getTimeZone(national));
        String sDateInAmerica = sdfAmerica.format(new Date());
        return sDateInAmerica;
    }
}
