package com.mcr.agendauacm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends Activity {

    private LottieAnimationView lottieSplash;
    private static final int DURACION_SPLASH=1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },DURACION_SPLASH);


        lottieSplash=findViewById(R.id.lottieSplash);
        lottieSplash.setImageAssetsFolder("assets");
        lottieSplash.setAnimation("5.json");




    }
}
