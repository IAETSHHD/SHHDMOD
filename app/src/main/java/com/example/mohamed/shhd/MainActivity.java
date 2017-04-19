package com.example.mohamed.shhd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;




public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar pbar = (ProgressBar) findViewById(R.id.progressBar);


        new CountDownTimer(3000,1000) {

            public void onTick(long millisUntilFinished) {
                pbar.setVisibility(View.VISIBLE);
            }

            public void onFinish() {
                pbar.setVisibility(View.VISIBLE);
                Intent i = new  Intent(getApplicationContext(),login_activity.class);
                startActivity(i);
                finish();
            }
        }.start();





    }

}

