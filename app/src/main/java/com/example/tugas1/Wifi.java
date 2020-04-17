package com.example.tugas1;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class Wifi extends Application {
    public static final String CONNECT = "Connect";
    public static final String DISCONNECT = "Disconnect";

    @Override
    public void onCreate() {
        super.onCreate();
        notification();
    }

    private void notification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel connect = new NotificationChannel(
                    CONNECT,
                    "Connect",
                    NotificationManager.IMPORTANCE_HIGH);//importance high diprioritas tingkat tinggi
            connect.setDescription("Connect");

            NotificationChannel disconnect = new NotificationChannel(
                    DISCONNECT,
                    "Disconnect",
                    NotificationManager.IMPORTANCE_LOW);
            disconnect.setDescription("Disconnect");

            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(connect);
            manager.createNotificationChannel(disconnect);
        }
    }
}
