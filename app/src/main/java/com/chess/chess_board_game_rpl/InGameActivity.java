package com.chess.chess_board_game_rpl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

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

        // Making a HashMap to make everything more efficient
        Map<String, Integer> pieceImageMap = new HashMap<>();
        pieceImageMap.put("PAWN_BLACK", R.drawable.pawn_black);
        pieceImageMap.put("PAWN_WHITE", R.drawable.pawn_white);
        pieceImageMap.put("ROOK_BLACK", R.drawable.rook_black);
        pieceImageMap.put("ROOK_WHITE", R.drawable.rook_white);
        pieceImageMap.put("KNIGHT_BLACK", R.drawable.knight_black);
        pieceImageMap.put("KNIGHT_WHITE", R.drawable.knight_white);
        pieceImageMap.put("BISHOP_WHITE", R.drawable.bishop_white);
        pieceImageMap.put("BISHOP_BLACK", R.drawable.bishop_black);
        pieceImageMap.put("KING_WHITE", R.drawable.king_white);
        pieceImageMap.put("KING_BLACK", R.drawable.king_black);
        pieceImageMap.put("QUEEN_WHITE", R.drawable.queen_white);
        pieceImageMap.put("QUEEN_BLACK", R.drawable.queen_black);
        // Add other pieces here

        GameBoard gameBoard = new GameBoard(); //Initialize the board
        GridLayout chessBoard = findViewById(R.id.chessBoard);
        String squareTag; //Square tag is to track where the square should be

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                ImageView square = new ImageView(this);
                Square gameSquare = GameBoard.getSquare(row, col); //Get the square data from row and column
                squareTag = row + "," + col; //Square tag consist of row,col for example row 1 and column 1 will be 1,1

                square.setTag(squareTag); //Set the tag

                // Set the background color or image depending on the square's color
                if ((row + col) % 2 == 0) {
                    square.setBackgroundColor(getResources().getColor(R.color.light_square_color)); // Light color
                } else {
                    square.setBackgroundColor(getResources().getColor(R.color.dark_square_color)); // Dark color
                }

                // Set the image based on the state of the square in the GameBoard
                assert gameSquare != null;
                if (gameSquare.isOccupied()) { //Check if the square is occupied
                    Piece piece = gameSquare.getOccupiedBy(); //Get the pieces data if occupied
                    String pieceType = piece.getClass().getSimpleName().toUpperCase(); // e.g., "PAWN", "ROOK", "KNIGHT"
                    String pieceColor = piece.getColor().toUpperCase(); // e.g., "BLACK", "WHITE"
                    String key = pieceType + "_" + pieceColor; // e.g., "PAWN_BLACK", "ROOK_WHITE"

                    if (pieceImageMap.containsKey(key)) {
                        square.setImageResource((Integer) pieceImageMap.get(key));
                    }
                }

                // Handle when the square is clicked
                square.setOnClickListener(v -> { // This is when the square is clicked
                    String tag = (String) v.getTag();
                    String[] parts = tag.split(","); // Split it and make it into variable
                    int clickedRow = Integer.parseInt(parts[0]);
                    int clickedCol = Integer.parseInt(parts[1]);

                    Square clickedSquare = gameBoard.getSquare(clickedRow, clickedCol); //Get the square data from clicked square
                    // Square changeSquare;

                    // Check if no piece is currently selected
                    // This condition is true when the player has not yet clicked on a piece, indicating the beginning of the action to select a piece on the board.
                    if (selectedSquareWrapper.square == null) {

                        // First click - Selecting the pieces
                        assert clickedSquare != null;
                        if (clickedSquare.isOccupied() & clickedSquare.getOccupiedBy() != null) {
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

                        Log.d("ChessDebug", gameBoard.getCurrentPlayer() + " Nani " + selectedSquareWrapper.square.getOccupiedBy().getColor());

                        Log.d("ChessDebug", String.valueOf(selectedSquareWrapper.square.getOccupiedBy().getColor().equals(gameBoard.getCurrentPlayer())));
                        if (selectedSquareWrapper.square.getOccupiedBy().getColor().equals(gameBoard.getCurrentPlayer())){
                            // Second click - Attempting to move the piece
                            // Reset if invalid
                            if (selectedSquareWrapper.square.getOccupiedBy().validMove(selectedSquareWrapper.square, clickedSquare,gameBoard,this)) {
                                assert clickedSquare != null; // Just to make sure if somehow the clicked square is NULL
                                movePiece(selectedSquareWrapper.square, clickedSquare, selectedSquareWrapper.view, (ImageView) v,pieceImageMap); //At
                                selectedSquareWrapper.square = null; // Reset
                                selectedSquareWrapper.view = null;
                                gameBoard.switchTurn();

                            } else {
                                // Move is invalid
                                selectedSquareWrapper.square = null; // Reset
                                selectedSquareWrapper.view = null;
                                Toast.makeText(getApplicationContext(), "Invalid move", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            selectedSquareWrapper.square = null; // Reset
                            selectedSquareWrapper.view = null;
                            Toast.makeText(getApplicationContext(),"Not Your Turn Currently " + gameBoard.getCurrentPlayer()+ " Turn",Toast.LENGTH_SHORT).show();
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
    private void movePiece(Square fromSquare, Square toSquare, ImageView fromView, ImageView toView,Map pieceImageMap) {

        Piece piece = fromSquare.getOccupiedBy(); //The square need to be checked about its content
        String pieceType = piece.getClass().getSimpleName().toUpperCase(); // e.g., "PAWN", "ROOK", "KNIGHT"
        String pieceColor = piece.getColor().toUpperCase(); // e.g., "BLACK", "WHITE"
        String key = pieceType + "_" + pieceColor; // e.g., "PAWN_BLACK", "ROOK_WHITE"

        //Unique Cases should be put here
        if (piece instanceof Pawn && ((Pawn) piece).isFirstMove()) {
            ((Pawn) piece).setFirstMove(false);
        }

        // Move the pieces
        if (pieceImageMap.containsKey(key)) {
            toView.setImageResource((Integer) pieceImageMap.get(key));
        }

        toSquare.setOccupiedBy(fromSquare.getOccupiedBy()); //Change the next square pieces content from null to the previously clicked square
        toSquare.setOccupied(true);
        fromSquare.setOccupiedBy(null); //Reset the previous clicked square to default
        fromSquare.setOccupied(false);
        fromView.setImageDrawable(null); // Clear the image from the original square

    }
}
