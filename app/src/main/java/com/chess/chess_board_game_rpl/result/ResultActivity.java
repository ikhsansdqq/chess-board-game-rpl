package com.chess.chess_board_game_rpl.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.chess.chess_board_game_rpl.R;
import com.chess.chess_board_game_rpl.history.ChessDatabaseHelper;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ChessDatabaseHelper chessDatabaseHelper = new ChessDatabaseHelper(this);
        chessDatabaseHelper.fetchMovesForGame(1);
    }
}