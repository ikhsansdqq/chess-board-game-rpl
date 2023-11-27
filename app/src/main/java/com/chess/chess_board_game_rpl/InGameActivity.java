package com.chess.chess_board_game_rpl;

import android.content.Intent;
import android.net.ipsec.ike.ChildSessionCallback;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        // Retrieve the difficulty level from the intent
        Intent intent = getIntent();
        String difficultyLevel = intent.getStringExtra("difficulty");

        // Now you can initialize your game board and adjust settings based on the difficulty level
        setupGameBoard();
        //setGameDifficulty(difficultyLevel);

    }

    public static class SquareWrapper { //This is the wrapper for clicked square
        public Square square;
        public ImageView view;
        public SquareWrapper(Square square, ImageView view) {
            this.square = square;
            this.view = view;
        }
    }

    private SquareWrapper selectedSquareWrapper = new SquareWrapper(null, null);
    private void setupGameBoard() {
        GameBoard gameBoard = new GameBoard(); //Initialize the board
        GridLayout chessBoard = findViewById(R.id.chessBoard);
        String squareTag; //Square tag is to track where the square should be

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                ImageView square = new ImageView(this);
                Square gameSquare = GameBoard.getSquare(row, col); //Get the square data from row and column
                squareTag = row + "," + col; //Square tag consist of row,col for example row 1 and column 1 will be 1,1

                square.setTag(squareTag); //Set the tag

                // Set the image based on the state of the square in the GameBoard
                assert gameSquare != null;
                if (gameSquare.isOccupied()) { //Check if the square is occupied
                    Piece piece = gameSquare.getOccupiedBy(); //Get the pieces data if occupied
                        if (piece instanceof Pawn){
                            if (Objects.equals(piece.getColor(), "BLACK")){
                                square.setImageResource(R.drawable.pawn_black);
                            }else if (Objects.equals(piece.getColor(),"WHITE")){
                                square.setImageResource(R.drawable.pawn_white);
                            }
                        }else if (piece instanceof Rook){
                            if (Objects.equals(piece.getColor(), "BLACK")){
                                square.setImageResource(R.drawable.rook_black);
                            }else if (Objects.equals(piece.getColor(),"WHITE")){
                                square.setImageResource(R.drawable.rook_white);
                            }
                        }
                }
                // Set the background color or image depending on the square's color
                if ((row + col) % 2 == 0) {
                    square.setBackgroundColor(getResources().getColor(R.color.light_square_color)); // Light color
                } else {
                    square.setBackgroundColor(getResources().getColor(R.color.dark_square_color)); // Dark color
                }

                square.setOnClickListener(v -> { //This is when the square is clicked
                    String tag = (String) v.getTag();
                    String[] parts = tag.split(","); //Split it and make it into variable
                    int clickedRow = Integer.parseInt(parts[0]);
                    int clickedCol = Integer.parseInt(parts[1]);

                    Square clickedSquare = gameBoard.getSquare(clickedRow, clickedCol); //Get the square data from clicked square
                    Square changeSquare;

                    // Check if no piece is currently selected
                    // This condition is true when the player has not yet clicked on a piece, indicating the beginning of the action to select a piece on the board.
                    if (selectedSquareWrapper.square == null) {
                        // First click - Selecting the pieces
                        Log.d("ChessDebug", String.valueOf(clickedSquare.getOccupiedBy() instanceof Piece));
                        if (clickedSquare.isOccupied() & clickedSquare.getOccupiedBy() instanceof Piece) { // TODO Making it more maintainble by making it universal instead of each pawn
                            selectedSquareWrapper.square = clickedSquare; //The wrapper
                            selectedSquareWrapper.view = (ImageView) v;

                            // TODO make a view limit
                            /*
                            if (Objects.equals(clickedSquare.getOccupiedBy().getColor(), "BLACK")){
                                for(int count = selectedSquareWrapper.square.getYPosition() + 1 ; count <= selectedSquareWrapper.square.getYPosition() + 2 ; count++) {
                                    changeSquare = gameBoard.getSquare(clickedRow,count);
                                    selectedSquareWrapper.view.setBackgroundColor(getResources().getColor(R.color.black));
                                }
                            }
                            */
                            //Log.d("ChessDebug", "Clicked Square: Row = " + clickedRow + ", Col = " + clickedCol);

                        }
                    } else {
                        // Second click - Attempting to move the piece
                        // Reset if invalid
                        if (selectedSquareWrapper.square.getOccupiedBy().validMove(selectedSquareWrapper.square, clickedSquare,this)) {

                            if (selectedSquareWrapper.square.getOccupiedBy() instanceof Pawn) {
                                assert clickedSquare != null; // Just to make sure if somehow the clicked square is NULL
                                movePawn(selectedSquareWrapper.square, clickedSquare, selectedSquareWrapper.view, (ImageView) v); //At
                           }else if (selectedSquareWrapper.square.getOccupiedBy() instanceof Rook){
                                assert clickedSquare != null; // Just to make sure if somehow the clicked square is NULL
                                moveRook(selectedSquareWrapper.square, clickedSquare, selectedSquareWrapper.view, (ImageView) v); //At
                            }
                            selectedSquareWrapper.square = null; // Reset after moving
                            selectedSquareWrapper.view = null;
                        } else {
                            // Move is invalid
                            Toast.makeText(getApplicationContext(), "Invalid move", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // Add LayoutParams to control the size of the squares
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = getResources().getDimensionPixelSize(R.dimen.chess_square_size); // Define this dimension in dimens.xml
                params.height = getResources().getDimensionPixelSize(R.dimen.chess_square_size); // Define this dimension in dimens.xml
                square.setLayoutParams(params);

                // Finally, add the view to your GridLayout
                chessBoard.addView(square);
            }
        }
    }
    private void movePawn(Square fromSquare, Square toSquare, ImageView fromView, ImageView toView) {

        Piece piece = fromSquare.getOccupiedBy(); //The square need to be checked about its content

        // Move the pawn on the board
        if (piece instanceof Pawn){
            Pawn pawn = (Pawn) piece;

            if(pawn.isFirstMove()){ // Pawn is special case
                pawn.setFirstMove(false);
            }

            toSquare.setOccupiedBy(fromSquare.getOccupiedBy()); //Change the next square pieces content from null to the previously clicked square
            toSquare.setOccupied(true);
            fromSquare.setOccupiedBy(null); //Reset the previous clicked square to default
            fromSquare.setOccupied(false);
            fromView.setImageDrawable(null); // Clear the image from the original square

            if (Objects.equals(piece.getColor(), "BLACK")){
                toView.setImageResource(R.drawable.pawn_black); // Set the image source to the right color of pawn
            }
            if (Objects.equals(piece.getColor(),"WHITE")){
                toView.setImageResource(R.drawable.pawn_white); // Set the image source to the right color of pawn
            }

        }
    }

    private void moveRook(Square fromSquare, Square toSquare, ImageView fromView, ImageView toView){

    }
}
