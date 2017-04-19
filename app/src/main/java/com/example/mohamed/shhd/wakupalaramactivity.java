package com.example.mohamed.shhd;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.CheckBox;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class wakupalaramactivity extends AppCompatActivity {

    private FirebaseDatabase alarms = FirebaseDatabase.getInstance();
    private final DatabaseReference thour=alarms.getReference("alarms").child("Hours");
    private final DatabaseReference tminutes=alarms.getReference("alarms").child("Minutes");
    private DatabaseReference checkboxes;
    private DatabaseReference ch1;
    private DatabaseReference ch2;
    private DatabaseReference ch3;
    private TimePicker timePicker1;

    private Calendar calendar;
    private String format = "";
    int r1=0;
    int r2=0;
    int r3=0;
    public Button save;
    public int hour1,min1;
    String hour4,min3;
    public CheckBox room1,room2,room3;
    public TextView hourshow,minshow,formatshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wakeupalarm);
        save=(Button)findViewById(R.id.set_button);
        checkboxes=alarms.getReference("alarms").child("checkboxes");
        ch1=checkboxes.child("check 1");
        ch2=checkboxes.child("check 2");
        ch3=checkboxes.child("check 3");
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        hourshow = (TextView) findViewById(R.id.hourss);
        minshow=(TextView)findViewById(R.id.minss);
        formatshow=(TextView)findViewById(R.id.duration);
        calendar = Calendar.getInstance();
        room1 = (CheckBox) findViewById(R.id.checkBox);
        getdata();
        room1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    r1=1;
                Toast.makeText(wakupalaramactivity.this,"ROOM1Checked", Toast.LENGTH_LONG).show();
            }
        });
        //Toast.makeText(MainActivity.this,"ROOM1Checked", Toast.LENGTH_LONG).show();
        room2 = (CheckBox) findViewById(R.id.checkBox2);
        room2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    r2=1;
                Toast.makeText(wakupalaramactivity.this,"ROOM2Checked", Toast.LENGTH_LONG).show();
            }
        });
        room3 = (CheckBox) findViewById(R.id.checkBox3);
        room3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    r3=1;
                Toast.makeText(wakupalaramactivity.this,"ROOM3Checked", Toast.LENGTH_LONG).show();
            }
        });
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter(hour1,min1);
                setTime(v);
                thour.setValue(String.valueOf(hour1));
                tminutes.setValue(String.valueOf(min1));
                ch1.setValue(room1.isChecked());
                ch2.setValue(room2.isChecked());
                ch3.setValue(room3.isChecked());
            }
        });
    }

    public void setTime(View view) {
        hour1 = timePicker1.getCurrentHour();
        min1 = timePicker1.getCurrentMinute();
        showTime(hour1, min1);
        counter(hour1,min1);

    }
    public void counter(int targeth, int targetm)
    {
        int remaining_hours= targeth-(calendar.get(Calendar.HOUR_OF_DAY));
        int remaining_mins= targetm- (calendar.get(Calendar.MINUTE));
        if (remaining_hours<0  && remaining_mins<0)
            Toast.makeText(this,"Wrong Value",Toast.LENGTH_LONG).show();
        if (remaining_hours>=0  && remaining_mins>=0)
            Toast.makeText(this,"Alarm will be after\n"+remaining_hours+" H:"+remaining_mins+" M",Toast.LENGTH_LONG).show();}

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        hourshow.setText(String.valueOf(hour1));
        minshow.setText(String.valueOf(min1));
        formatshow.setText(format);
    }
    public void getdata(){
        thour.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hour3=dataSnapshot.getValue(String.class);
                hourshow.setText(hour3);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        tminutes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                min3=dataSnapshot.getValue(String.class);
                minshow.setText(min3);
                formatshow.setText(null);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ch1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                room1.setChecked(dataSnapshot.getValue(boolean.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ch2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                room2.setChecked(dataSnapshot.getValue(boolean.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ch3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                room3.setChecked(dataSnapshot.getValue(boolean.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        showTime(hour1,min1);
    }



}
//@Override
//public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
//getMenuInflater().inflate(R.menu.main, menu);
// return true;
