package com.example.admin.stickspeak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
    }

    public void talkText(View view) {
        Toast.makeText(this,"Speak Click" , Toast.LENGTH_LONG).show();

    }

    public void sentText(View view){
        Toast.makeText(this,"SentText",Toast.LENGTH_LONG).show();

    }





}
