package com.chess.chess_board_game_rpl;

import android.os.Bundle;
import android.content.Intent;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

/* public class InGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Game Activity");
        toolbar.setSubtitle("In game for: 12 minutes");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

 */

public class InGameActivity extends AppCompatActivity {

    private String difficultyLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        // Retrieve the difficulty level from the intent
        Intent intent = getIntent();
        difficultyLevel = intent.getStringExtra("difficulty");

        // Now you can initialize your game board and adjust settings based on the difficulty level
        setupGameBoard();
        //setGameDifficulty(difficultyLevel);
    }

    private void setupGameBoard() {
        GridLayout chessBoard = findViewById(R.id.chessBoard);
        int totalSquares = 8 * 8; // total squares for an 8x8 chessboard

        for (int i = 0; i < totalSquares; i++) {
            ImageView square = new ImageView(this);
            // Set the background color or image depending on the square's color
            if ((i / 8) % 2 == (i % 8) % 2) {
                square.setBackgroundColor(getResources().getColor(R.color.light_square_color)); // Define this color in your colors.xml
            } else {
                square.setBackgroundColor(getResources().getColor(R.color.dark_square_color)); // Define this color in your colors.xml
            }

            // Set the image for the piece if there is one
            if (i == (1 * 8) + 0) {
                square.setImageResource(R.drawable.pawn_black); // Replace with your actual drawable name
            }
            // You can use tags or a separate method to determine what piece, if any, is on this square

            // Add LayoutParams to control the size of the squares
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = getResources().getDimensionPixelSize(R.dimen.chess_square_size); // Define this dimension in your dimens.xml
            params.height = getResources().getDimensionPixelSize(R.dimen.chess_square_size); // Define this dimension in your dimens.xml
            square.setLayoutParams(params);

            // Finally, add the view to your GridLayout
            chessBoard.addView(square);
        }
    }

}