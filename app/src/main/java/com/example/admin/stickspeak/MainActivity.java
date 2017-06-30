package com.example.admin.stickspeak;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

     int time = 3000;  // 1000 =  1 second

    @Override
    public void onCreate(Bundle splash_test) {
        super.onCreate(splash_test);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(MainActivity.this,   Main2Activity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, time);
    }



    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
