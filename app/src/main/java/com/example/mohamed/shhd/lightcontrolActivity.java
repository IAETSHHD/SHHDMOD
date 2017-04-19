package com.example.mohamed.shhd;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;


public class lightcontrolActivity extends AppCompatActivity implements OnCheckedChangeListener,View.OnClickListener {
    //some variable gonna need through this activity
    private Switch s;
    private Switch s1;
    private Switch s2;
    private Switch s3;
    private Switch s4;
    private Switch s5;
    private Button invs;
    private EditText countdown;
    private String x;
    CountDownTimer cd2;
    String time;
    long timer;
    long millis;
    private boolean k=false;
    private DatabaseReference lightcontrol ;
    private DatabaseReference switches;
    private DatabaseReference switch1 ;
    private DatabaseReference switch2 ;
    private DatabaseReference switch3 ;
    private DatabaseReference switch4 ;
    private DatabaseReference switch5 ;
    private DatabaseReference switch6 ;
    private DatabaseReference reversecounter ;
    private DatabaseReference reversecounterchecker ;
    private DatabaseReference buttonname ;
    private background task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lightcontrol);
        lightcontrol = FirebaseDatabase.getInstance().getReference();
        switches = lightcontrol.child("Switches");
        switch1 = switches.child("Switch_1_is");
        switch2 = switches.child("Switch_2_is");
        switch3 = switches.child("Switch_3_is");
        switch4 = switches.child("Switch_4_is");
        switch5 = switches.child("Switch_5_is");
        switch6 = switches.child("Switch_6_is");
        reversecounter = switches.child("Reverse_counter_time_is");
        reversecounterchecker = switches.child("Reverse_counter_checker_is");
        buttonname = switches.child("Button_name_is");
        task = new background();
        var();
        loadstatus();
        s.setOnCheckedChangeListener(this);
        s1.setOnCheckedChangeListener(this);
        s2.setOnCheckedChangeListener(this);
        s3.setOnCheckedChangeListener(this);
        s4.setOnCheckedChangeListener(this);
        s5.setOnCheckedChangeListener(this);
        invs.setOnClickListener(this);
        countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayKeyboard();
            }
        });
    }

    private void displayKeyboard(){
        if (countdown != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInputFromWindow(countdown.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        }
    }
    //what happen when changing switches state
    @Override
    public void onCheckedChanged(CompoundButton x, boolean isChecked) {
        if (x == s) {
            savestatus(s);
        }
        if (x == s1) {
            savestatus(s1);
        }
        if (x == s2) {
            savestatus(s2);
        }
        if (x == s3) {
            savestatus(s3);
        }
        if (x == s4){
            savestatus(s4);
        }
        if (x == s5){
            savestatus(s5);
        }
    }
    //save status for switches
    public void savestatus(CompoundButton x){
        if(x==s){
            switch1.setValue(s.isChecked());
        }
        if(x==s1){
            switch2.setValue(s1.isChecked());
        }
        if(x==s2){
            switch3.setValue(s2.isChecked());
        }
        if(x==s3){
            switch4.setValue(s3.isChecked());
        }
        if(x==s4){
            switch5.setValue(s4.isChecked());
        }
        if(x==s5){
            switch6.setValue(s5.isChecked());
        }
    }
    //load status for switches
    public void loadstatus(){
        reversecounter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String x = dataSnapshot.getValue(String.class);
                countdown.setText(x);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        String x=String.valueOf(countdown.getText());
        if(x == "00:00:01")
        {
            reversecounterchecker.setValue(false);
            buttonname.setValue("start");
        }
        if(x == "00:00:00")
        {
            reversecounterchecker.setValue(false);
            buttonname.setValue("start");
        }
        reversecounterchecker.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                k = dataSnapshot.getValue(boolean.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        buttonname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String x = dataSnapshot.getValue(String.class);
                invs.setText(x);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        switch1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean a = dataSnapshot.getValue(boolean.class);
                s.setChecked(a);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        switch2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean a = dataSnapshot.getValue(boolean.class);
                s1.setChecked(a);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        switch3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean a = dataSnapshot.getValue(boolean.class);
                s2.setChecked(a);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        switch4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean a = dataSnapshot.getValue(boolean.class);
                s3.setChecked(a);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        switch5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean a = dataSnapshot.getValue(boolean.class);
                s4.setChecked(a);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        switch6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean a = dataSnapshot.getValue(boolean.class);
                s5.setChecked(a);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    //give each variable the view it's representing
    public void var(){
        s = (Switch) findViewById(R.id.mySwitch);
        s1 = (Switch) findViewById(R.id.mySwitch1);
        s2 = (Switch) findViewById(R.id.mySwitch2);
        s3 = (Switch) findViewById(R.id.mySwitch3);
        s4 = (Switch) findViewById(R.id.mySwitch4);
        s5 = (Switch) findViewById(R.id.mySwitch5);
        invs = (Button) findViewById(R.id.start_inv);
        countdown = (EditText) findViewById(R.id.timer);
    }
    //what happen when clicking on button
    @Override
    public void onClick(View v) {
        if(v==invs) {
            try{
                String y = String.valueOf(countdown.getText());
                String[] x = y.split(":");
                int[] z = new int[x.length];
                for (int i = 0; i < x.length; i++) {
                    z[i] = Integer.parseInt(x[i]);
                }
                timer = (z[0] * 3600 + z[1] * 60 + z[2]) * 1000;
                if(!k){
                    k = true;
                    reversecounterchecker.setValue(k);
                    invs.setText("stop");
                    buttonname.setValue("stop");
                    task.doInBackground("");
                }
                else{
                    task.onCancelled("");
                    invs.setText("start");
                    buttonname.setValue("start");
                    k=false;
                    reversecounterchecker.setValue(k);
                }}
            catch (Exception e){
                new AlertDialog.Builder(lightcontrolActivity.this)
                        .setTitle(getString(R.string.app_name))
                        .setMessage("Please input as a form of\n hh:mm:ss")
                        .setIcon(R.drawable.aa_app_logo)
                        .setNeutralButton("Okay", null).show();
            }
        }
    }
    //class to work things in background so it don't get stopped even if i changed activity but still gonna stopped if user close the application
    class background extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            cd2=new CountDownTimer(timer,1000) {

                public void onTick(long millisUntilFinished) {
                    millis=millisUntilFinished;
                    time = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    countdown.setText(time);
                    reversecounter.setValue(time);
                }

                public void onFinish() {
                    k=false;
                    reversecounterchecker.setValue(k);
                    rev();
                    invs.setText("start");
                    buttonname.setValue("start");
                    Intent intent = new Intent(getApplicationContext(), lightcontrolActivity.class);
                    PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                    Notification n = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Smart Home handler ")
                            .setContentText("All Rooms inverted")
                            .setSmallIcon(R.drawable.notify)
                            .setContentIntent(pIntent)
                            //.setColor(getResources().getColor(R.color.blue))
                            .setAutoCancel(true)
                            .build();
                    NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0, n);
                }
            }.start();
            return null;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            cd2.cancel();
        }
    }
    //method to reverse state of each switch
    public void rev(){
        if (s.isChecked()) {
            s.setChecked(false);
            savestatus(s);
        }
        else
        {
            s.setChecked(true);
            savestatus(s);
        }
        if (s1.isChecked()) {
            s1.setChecked(false);
            savestatus(s1);
        }
        else
        {
            s1.setChecked(true);
            savestatus(s1);
        }
        if (s2.isChecked()) {
            s2.setChecked(false);
            savestatus(s2);
        }
        else
        {
            s2.setChecked(true);
            savestatus(s2);
        }
        if (s3.isChecked()) {
            s3.setChecked(false);
            savestatus(s3);
        }
        else
        {
            s3.setChecked(true);
            savestatus(s3);
        }
        if (s4.isChecked()) {
            s4.setChecked(false);
            savestatus(s4);
        }
        else
        {
            s4.setChecked(true);
            savestatus(s4);
        }
        if (s5.isChecked()) {
            s5.setChecked(false);
            savestatus(s5);
        }
        else
        {
            s5.setChecked(true);
            savestatus(s5);
        }
    }
}




