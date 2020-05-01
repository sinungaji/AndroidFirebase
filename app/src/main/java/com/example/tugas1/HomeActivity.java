package com.example.tugas1;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.BroadcastReceiver;

import static com.example.tugas1.Wifi.CONNECT;
import static com.example.tugas1.Wifi.DISCONNECT;
import com.google.android.material.tabs.TabLayout;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.widget.Button;
import android.view.View;
import android.content.res.Configuration;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private NotificationManagerCompat notificationCompat;
    Button mCaputreBtn;
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    Uri image_uri;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);




        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabCount();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String buddle_isi = bundle.getString("KEY","");
            Toast.makeText(getApplicationContext(),buddle_isi,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            // Toast.makeText(getActivity(),"PORTRAIT",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "POTRAIT", Toast.LENGTH_SHORT).show();
            //add your code what you want to do when screen on PORTRAIT MODE
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            //Toast.makeText(getActivity(),"LANDSCAPE",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "LANDSCAPE", Toast.LENGTH_SHORT).show();
            //add your code what you want to do when screen on LANDSCAPE MODE
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(wificheck, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wificheck);
    }
    private BroadcastReceiver wificheck = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiStateExtra){
                case WifiManager.WIFI_STATE_ENABLED:
                    android.app.Notification notification = new NotificationCompat.Builder(HomeActivity.this,CONNECT)
                            .setSmallIcon(R.drawable.ic_wifi)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .setContentTitle("Wifi")
                            .setContentText("Terhubung dengan Wifi")
                            .build();
//                    notificationCompat.notify(1,notification);
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    android.app.Notification notification2 = new NotificationCompat.Builder(HomeActivity.this,DISCONNECT)
                            .setSmallIcon(R.drawable.ic_wifi)
                            .setPriority(NotificationCompat.PRIORITY_LOW)
                            .setContentTitle("Wifi")
                            .setContentText("Terputus dengan Wifi")
                            .build();
//                    notificationCompat.notify(2,notification2);
                    break;
            }
        }
    };
}
