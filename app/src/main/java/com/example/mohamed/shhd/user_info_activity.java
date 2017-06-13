package com.example.mohamed.shhd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_info_activity extends item implements View.OnClickListener {

    CheckBox checkBoxA;
    CheckBox checkBoxB;
    CheckBox checkBoxC;
    EditText Name_info;
    EditText age_info;
    Button Save_info;
    private DatabaseReference user_name;
    private DatabaseReference User_age ;
    private DatabaseReference User_type;
    DatabaseReference checker;
    boolean k = false;
    DatabaseReference Userinfo2 ;
    DatabaseReference Userinfo ;
    private ProgressDialog progressDialog;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_layout);
        checkBoxA = (CheckBox) findViewById(R.id.checkBoxA);
        checkBoxB = (CheckBox) findViewById(R.id.checkBoxB);
        checkBoxC = (CheckBox) findViewById(R.id.checkBoxC);
        Name_info = (EditText) findViewById(R.id.reg_Name);
        age_info = (EditText) findViewById(R.id.reg_age);
        Save_info = (Button) findViewById(R.id.Save_register);
        user = FirebaseAuth.getInstance().getCurrentUser();
        checkBoxA.setOnClickListener(this);
        checkBoxB.setOnClickListener(this);
        checkBoxC.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        Save_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker.setValue(true);
                savestatus();
                startActivity(new Intent(getApplicationContext(), StartActivity.class));
                finish();
            }
        });
        Userinfo = FirebaseDatabase.getInstance().getReference();
        if (user.getDisplayName() != null) {
            Userinfo2 = Userinfo.child("User Info").child(user.getDisplayName());
        } else {
            Userinfo2 = Userinfo.child("User Info").child(user.getUid());
        }
        user_name = Userinfo2.child("Name");
        User_age = Userinfo2.child("Age");
        User_type = Userinfo2.child("Type");
        checker = Userinfo2.child("checker");
        progressDialog.setMessage("loading Please Wait...");
        progressDialog.show();
        checker.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    boolean x = dataSnapshot.getValue(boolean.class);
                    if (x) {
                        progressDialog.dismiss();
                        finish();
                        startActivity(new Intent(getApplicationContext(), StartActivity.class));
                    }

                }
                catch (Exception e)
                {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*new CountDownTimer(4000,1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
            }
        }.start();*/

    }
    void savestatus()
    {
        user_name.setValue(String.valueOf(Name_info.getText()));
        User_age.setValue(String.valueOf(age_info.getText()));
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.checkBoxA:
                User_type.setValue("Normal");
                checkBoxB.setChecked(false);
                checkBoxC.setChecked(false);


                break;

            case R.id.checkBoxB:
                User_type.setValue("Deaf");
                checkBoxC.setChecked(false);
                checkBoxA.setChecked(false);

                break;

            case R.id.checkBoxC:
                User_type.setValue("Blind");
                checkBoxB.setChecked(false);
                checkBoxA.setChecked(false);

                break;

        }
    }
}
