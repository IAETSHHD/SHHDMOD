package com.example.mohamed.shhd;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homehandlerActivity extends AppCompatActivity {
    Button Handler_light_control, Handler_alarm, Handler_take_photo, Handler_battery,button_deaf,button_robot_test;

    FirebaseAuth firbas;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homehandler);

        Handler_light_control = (Button) findViewById(R.id.Light_control);
        Handler_alarm = (Button) findViewById(R.id.Wake_up_alarm);
        Handler_take_photo = (Button) findViewById(R.id.Take_photos);
        Handler_battery = (Button) findViewById(R.id.Battery_level);
        button_deaf = (Button) findViewById(R.id.Deaf_k);
        button_robot_test = (Button) findViewById(R.id.Robot_test);
        firbas = FirebaseAuth.getInstance();
        FirebaseUser user = firbas.getCurrentUser();

        if (user.getUid().equals("lIH19M3rxRgtYAIZlPeWbxC8zHM2"))
        {
            button_deaf.setVisibility(View.VISIBLE);
        }



        Handler_light_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), lightcontrolActivity.class);
                startActivity(i);
            }
        });
        Handler_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), wakupalaramactivity.class);
                startActivity(i);
            }
        });
        Handler_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), takephotoActivity.class);
                startActivity(i);
            }
        });

        Handler_battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), batterylevelActivity.class);
                startActivity(i);
            }
        });
        button_deaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Deaf_talk_activity.class);
                startActivity(i);
            }
        });
        button_robot_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), wifi_test.class);
                startActivity(i);
            }
        });


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
}