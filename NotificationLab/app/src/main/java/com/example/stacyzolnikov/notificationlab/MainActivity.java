package com.example.stacyzolnikov.notificationlab;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.icu.text.IDNA;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
     NotificationCompat.Builder mBuilder;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 123, intent, 0);


        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        mBuilder.setContentTitle("Notification Alert!");
        mBuilder.setContentText("Your network connectivity:");
        mBuilder.setContentIntent(pIntent);
        mBuilder.setPriority(Notification.PRIORITY_MAX);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle();
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_wifi_black_24dp));
            mBuilder.setStyle(pictureStyle);
            NotificationManagerCompat.from(MainActivity.this).notify(456, mBuilder.build());
            mBuilder.setAutoCancel(true).setContentTitle("Connected");
        }
        else {
            NotificationCompat.BigPictureStyle pictureStyle2 = new NotificationCompat.BigPictureStyle();
            pictureStyle2.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_network_check_black_24dp));
            mBuilder.setStyle(pictureStyle2);
            NotificationManagerCompat.from(MainActivity.this).notify(456, mBuilder.build());
            mBuilder.setAutoCancel(false).setContentTitle("Not Connected");
        }

    }



    }

