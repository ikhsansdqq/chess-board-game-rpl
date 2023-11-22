package com.chess.chess_board_game_rpl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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
        //GameBoard gameBoard = new GameBoard();
        GridLayout chessBoard = findViewById(R.id.chessBoard);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ImageView square = new ImageView(this);

                /* Possible another implementation


                Square gameSquare = gameBoard.getSquare(row, col);

                // Set the image based on the state of the square in the GameBoard
                if (gameSquare.hasPiece()) {
                ChessPiece piece = gameSquare.getPiece();
                if (piece instanceof Pawn && piece.getColor() == Color.BLACK) {
                square.setImageResource(R.drawable.pawn_black);
                }
                // Add similar conditions for other types of pieces
                }
                 */
                // Set the background color or image depending on the square's color
                if ((row + col) % 2 == 0) {
                    square.setBackgroundColor(getResources().getColor(R.color.light_square_color)); // Light color for even sum
                } else {
                    square.setBackgroundColor(getResources().getColor(R.color.dark_square_color)); // Dark color for odd sum
                }

                if ((row == 1)){
                    square.setImageResource(R.drawable.pawn_black);
                }

                /*
                final int finalRow = row;
                final int finalCol = col;
                */
                square.setOnClickListener(v -> square.setBackgroundColor(getResources().getColor(R.color.black)));

                // Set the image for the piece if there is one


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
    //private void onSquareClicked(int row, int col){}

}
