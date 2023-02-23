package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.net.ssl.HttpsURLConnection;

public class welcome_screen extends AppCompatActivity {
    private static int splash_time_out=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences pref=getSharedPreferences(login.PREFS_NAME,0);
                boolean hasloggedin=pref.getBoolean("hasloggedin",false);
                if (hasloggedin){
                    Intent intent=new Intent(welcome_screen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent=new Intent(welcome_screen.this,login.class);
                    startActivity(intent);
                    finish();
                }
            }
        },splash_time_out);
    }
}