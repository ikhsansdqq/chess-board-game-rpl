package com.chess.chess_board_game_rpl;

public class GameBoard {
    private Square[][] squares = new Square[8][8];

    public GameBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        // Loop through the squares and initialize them
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square(i, j);
                // You might also place pieces here depending on your design
            }
        }
        // Place pieces on the board
        // This is just a placeholder, you'll need to create instances of the pieces and place them
    }

    public Square getSquare(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return squares[x][y];
        } else {
            return null; // or throw an exception if you prefer
        }
    }

    // Additional methods for moving pieces, checking the game state, etc.
}
