package com.alicearmstrong.coffeysloyaltyprojectv1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity
{
    // Set the splash time
    int SPLASH_TIME = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                // Open login screen
                Intent intent_startLoginScreen = new Intent(SplashScreen.this, LoginScreen.class);
                startActivity(intent_startLoginScreen);
                finish();

            }
        }, SPLASH_TIME);


    }


}


