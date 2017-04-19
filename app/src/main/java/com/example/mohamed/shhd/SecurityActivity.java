package com.example.mohamed.shhd;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SecurityActivity extends AppCompatActivity {
    private Switch security_mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security);
        security_mode = (Switch) findViewById(R.id.S_Mode);
        loadstatus();
        try{
            if(security_mode.isChecked())
                PushNotification("Security mode enabled");
            else
                PushNotification("Security mode disabled");
        }
        catch(Exception e){
            loadstatus();
        }
        security_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try{
                    if(!isChecked) {
                        PushNotification("Security mode disabled");
                    }
                    else {
                        PushNotification("Security mode enabled");
                    }
                }
                catch(Exception e){
                    savestatus(security_mode);
                }
            }
        });
    }
    public void savestatus(CompoundButton x){
        SharedPreferences z=getSharedPreferences("security", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = z.edit();
        editor.putString("security_mode", String.valueOf(x.isChecked()));
        editor.apply();
    }
    public void loadstatus(){
        SharedPreferences z=getSharedPreferences("security", Context.MODE_PRIVATE);
        security_mode.setChecked(Boolean.valueOf(z.getString("security_mode","")));
    }
    public void PushNotification(String s)
    {
        NotificationManager nm = (NotificationManager)getBaseContext().getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(getBaseContext());
        Intent notificationIntent = new Intent(getBaseContext(), SecurityActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(),0,notificationIntent,0);

        //set
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.drawable.notify);
        builder.setContentText(s);
        builder.setContentTitle("Smart Home Handler");
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_ALL);
        Notification notification = builder.build();
        int id=1;
        nm.notify(id,notification);
    }
}
