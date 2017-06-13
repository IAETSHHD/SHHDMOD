package com.example.mohamed.shhd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Deaf_talk_activity extends AppCompatActivity {

    EditText Deaf_t2s_edtx;
    Button Deaf_t2s_bt;
    FirebaseAuth firebaseAuth;
    private DatabaseReference Deaf_talk;
    private DatabaseReference Deaf_talk_2;
    private DatabaseReference Txt_speech_word ;
    boolean k = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deaf_talk_layout);
        Deaf_t2s_edtx = (EditText) findViewById(R.id.Text2speach);
        Deaf_t2s_bt = (Button) findViewById(R.id.t2s_button);
        firebaseAuth = FirebaseAuth.getInstance();
        Deaf_talk = FirebaseDatabase.getInstance().getReference();
        Deaf_talk_2 = Deaf_talk.child("Deaf_talk");
        Txt_speech_word = Deaf_talk_2.child("text_2_speech");
        Txt_speech_word.setValue(String.valueOf(Deaf_t2s_edtx.getText()));
        Deaf_t2s_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Txt_speech_word.setValue(String.valueOf(Deaf_t2s_edtx.getText()));
                Toast.makeText(getApplicationContext(),"Saved on server",Toast.LENGTH_LONG).show();
            }
        });
    }
}
