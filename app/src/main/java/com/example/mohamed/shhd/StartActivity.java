package com.example.mohamed.shhd;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {
    Button Start_Home_handler, Start_Disabled_people, Start_security, Start_mission_history, Chat;
    TextView Start_logout;
    private WifiManager wifiManager;
    private boolean wifi_checker;
    FirebaseAuth firbas;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firbas = FirebaseAuth.getInstance();
        FirebaseUser user = firbas.getCurrentUser();
        setContentView(R.layout.start);
        Start_Home_handler = (Button) findViewById(R.id.Home_Handler);
        Start_security = (Button) findViewById(R.id.Security);
        Start_mission_history = (Button) findViewById(R.id.Mission_history);
        Chat = (Button) findViewById(R.id.Chat);
        Start_logout = (TextView) findViewById(R.id.buttonlogoout);
        Start_logout.setPaintFlags(Start_logout.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Start_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firbas.signOut();
                //closing activity
                finish();
                //starting login activity
                startActivity(new Intent(getApplicationContext(), login_activity.class));
            }
        });
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        navigation=(NavigationView) findViewById(R.id.navigationview) ;
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*if (user.getUid().equals("X9lu5VaOQ2bnDLhnjIO0FxGKoqd2"))
        {
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        }*/

        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled())
            wifi_checker = true;
        else {
            wifi_checker = false;
            wifiManager.setWifiEnabled(true);
        }
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if (!mWifi.isConnected()) {
                    startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
                }
            }
        }.start();

        // startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));


        Start_Home_handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), homehandlerActivity.class);
                startActivity(i);
            }
        });

        Start_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                Notification n = new Notification.Builder(getApplicationContext())
                        .setContentTitle("Smart Home handler ")
                        .setContentText("you are now in security mode")
                        .setSmallIcon(R.drawable.login)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, n);
                Intent i = new Intent(getApplicationContext(), SecurityActivity.class);
                startActivity(i);
            }
        });
        Start_mission_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), missionhistoryActivity.class);
                startActivity(i);
            }
        });

        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(i);
            }
        });

        Start_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firbas.signOut();
                //closing activity
                finish();
                //starting login activity
                startActivity(new Intent(getApplicationContext(), login_activity.class));


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //Ask the user if they want to quit

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.aa_app_logo)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Stop the activity
                            if (!wifi_checker)
                                wifiManager.setWifiEnabled(false);
                            StartActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();


            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
    public void doThis(MenuItem item){
        if(item.getItemId()==R.id.nav_Settings){
            Intent i = new Intent(getApplicationContext(), missionhistoryActivity.class);
            startActivity(i);
            mDrawerLayout.closeDrawers();}
        if(item.getItemId()==R.id.nav_account){
            Intent i = new Intent(getApplicationContext(), homehandlerActivity.class);
            startActivity(i);
            mDrawerLayout.closeDrawers();}
        if(item.getItemId()==R.id.nav_Logout){
            firbas.signOut();
            //closing activity
            finish();
            Intent i = new Intent(getApplicationContext(), login_activity.class);
            startActivity(i);
            mDrawerLayout.closeDrawers();}
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}


