package com.chess.chess_board_game_rpl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

//    LOG TESTING
    private static final String TAG = "SPLASHSCREEN";

    ImageView logo, background;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Splash Screen is created");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.Logo);
        title = findViewById(R.id.Title);
        background = findViewById(R.id.background);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        logo.startAnimation(fadeInAnimation);
        title.startAnimation(fadeInAnimation);
        background.startAnimation(fadeInAnimation);

        int SPLASHSCREEN = 5000;
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Log.d(TAG, "onCreate: Navigate to HomeScreen()");

            Intent intent = new Intent(SplashScreen.this, HomeScreen.class);
            startActivity(intent);
            finish();
        }, SPLASHSCREEN);

    }
}
