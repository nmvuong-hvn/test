import tcp.IDataReceived;
import tcp.ServerTcp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class ServerApp extends JFrame implements IGetTimeCommon, Runnable, IDataReceived {
    private JPanel panel1;
    private JLabel iv_national1;
    private JLabel name_national1;
    private JLabel time_national1;
    private JLabel iv_national2;
    private JLabel name_national2;
    private JLabel time_national2;
    private JLabel iv_national3;
    private JLabel name_national3;
    private JLabel time_national3;
    private JLabel iv_national4;
    private JLabel name_national4;
    private JLabel time_national4;
    private JLabel iv_national5;
    private JLabel name_national5;
    private JLabel time_national5;
    private JComboBox comboBoxNational;
    private JButton btn_send;
    private JLabel iv_choice;
    private JLabel name_choice;
    String[] timeNational = {"Asia/Ho_Chi_Minh", "Asia/Jakarta", "Asia/Tokyo", "Asia/Seoul", "America/New_York"};

    String vietNam = getCurrentTime(timeNational[0]);
    String indonesia = getCurrentTime(timeNational[1]);
    String tokyo = getCurrentTime(timeNational[2]);
    String seoul = getCurrentTime(timeNational[3]);
    String us = getCurrentTime(timeNational[4]);
    int index = 0;

    Thread threadTimeNational;
    ServerTcp serverTcp;

    public ServerApp() {

        setTitle("Server - Dự báo thời tiết");
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        serverTcp = new ServerTcp();
        try {
            serverTcp.init(this);
            serverTcp.readData();
        } catch (Exception exception) {
            System.out.println("Error = " + exception.getMessage());
        }

        iv_national1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                index = 0;
                iv_choice.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/image/vietnam.png"))));
                name_choice.setText("Viet Nam");
            }
        });
        iv_national2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                index = 1;

                iv_choice.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/image/indonesia.png"))));
                name_choice.setText("Indonesia");
            }
        });
        iv_national3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                index = 2;

                iv_choice.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/image/japan.png"))));
                name_choice.setText("Japan");
            }
        });
        iv_national4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                index = 3;
                iv_choice.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/image/south_korea.png"))));
                name_choice.setText("Korea");
            }
        });
        iv_national5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                index = 4;

                iv_choice.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/image/usa.png"))));
                name_choice.setText("US");
            }
        });
        btn_send.addActionListener(e -> {
                    try {
                        serverTcp.writeData(timeNational[index]);
                        WeatherClient weatherClient = new WeatherClient();
                        weatherClient.setVisible(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );
        //thread implement time
        threadTimeNational = new Thread(() -> {
            while (true) {
                String vietNam = getCurrentTime(timeNational[0]);
                String indo = getCurrentTime(timeNational[1]);
                String tokyo = getCurrentTime(timeNational[2]);
                String seoul = getCurrentTime(timeNational[3]);
                String usa = getCurrentTime(timeNational[4]);
                name_national1.setText("Viet Nam");
                name_national2.setText("Indonesia");
                name_national3.setText("Japan");
                name_national4.setText("Korea");
                name_national5.setText("US");
                time_national1.setText(vietNam);
                time_national2.setText(indo);
                time_national3.setText(tokyo);
                time_national4.setText(seoul);
                time_national5.setText(usa);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadTimeNational.start();

    }

    public static void main(String[] args) {

        new ServerApp();
    }

    URL getUrlImage(String url) {
        System.out.println("urlCheck = " + url.toLowerCase(Locale.ROOT));
        System.out.println(this.getClass().getResource("/images/rain_64.png"));
        if (url.toLowerCase(Locale.ROOT).contains("rain")) {
            return this.getClass().getResource("/images/rain_64.png");
        } else if (url.toLowerCase(Locale.ROOT).contains("cloud")) {
            return this.getClass().getResource("/images/cloud_64.png");
        } else if (url.toLowerCase(Locale.ROOT).contains("snow")) {
            return this.getClass().getResource("/images/snowflake_64.png");

        } else if (url.toLowerCase(Locale.ROOT).contains("thunderstorm")) {
            return this.getClass().getResource("/images/thunderstorm_64.png");
        } else {
            return this.getClass().getResource("/images/clear_sky_64.png");
        }
    }

    @Override
    public String getCurrentTime(String national) {
        SimpleDateFormat sdfAmerica = new SimpleDateFormat("HH:mm:ss a");
        sdfAmerica.setTimeZone(TimeZone.getTimeZone(national));
        String sDateInAmerica = sdfAmerica.format(new Date());
        return sDateInAmerica;
    }

    @Override
    public void run() {

    }

    @Override
    public void receivedData(String data) {
        System.out.println("Server - " + data);
        if (data == "OK") {

        }
    }
}
