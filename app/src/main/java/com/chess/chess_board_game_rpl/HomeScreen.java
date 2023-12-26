package com.chess.chess_board_game_rpl;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // Make sure this is the correct layout with the chess_mode_normal ID
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayout normalMode = findViewById(R.id.chess_mode_normal); // Replace with the correct ID of your "Normal Mode" view

        // Set the click listeners for normal mode
        normalMode.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, MainActivity.class);
            // You can put extras if you need to pass some data
            // intent.putExtra("EXTRA_KEY", "Some Data");
            startActivity(intent);
        });

        // Set the click listeners for competitive mode
        // ...

        // Set the click listeners for fusion mode
        // ...
    }

}
