package com.chess.chess_board_game_rpl;

public class GameBoard {
    private static Square[][] squares = new Square[8][8];

    public GameBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        // Loop through the squares and initialize them
        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                squares[row][col] = new Square(row, col);

                if ((row == 1)){
                    Piece pawn_black = new Pawn("BLACK");
                    squares[row][col].setOccupiedBy(pawn_black); // Corrected line
                    squares[row][col].setOccupied(true);
                }
                // You might also place pieces here depending on your design
            }
        }
        // Place pieces on the board
        // This is just a placeholder, you'll need to create instances of the pieces and place them
    }

    public static Square getSquare(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return squares[x][y];
        } else {
            return null; // or throw an exception if you prefer
        }
    }

    // Additional methods for moving pieces, checking the game state, etc.
}
