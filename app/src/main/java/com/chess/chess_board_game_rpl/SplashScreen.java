package com.chess.chess_board_game_rpl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static  int SPLASH_SCREAN = 5000;

    //variabel nya taruh di sini
    ImageView logo, background;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //untuk memanggil screan yang kita mau
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        // Inisialisasi tampilan
        logo = findViewById(R.id.Logo); // Make sure this ID matches your ImageView ID for the logo in XML
        title = findViewById(R.id.Title); // Make sure this ID matches your TextView ID for the title in XML
        background = findViewById(R.id.background); // Make sure this ID matches your ImageView ID for the background in XML

        // memanggil animasi nya
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Animasi
        logo.startAnimation(fadeInAnimation);
        title.startAnimation(fadeInAnimation);
        background.startAnimation(fadeInAnimation);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, HomeScreen.class);
            startActivity(intent);
            finish(); // Jangan lupa untuk menutup SplashScreenActivity
        }, SPLASH_SCREAN);

    }
}
