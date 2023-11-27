package com.chess.chess_board_game_rpl;

public class GameBoard {

    private static Square[][] squares = new Square[8][8];
    private String piece_tag;

    public GameBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        // Loop through the squares and initialize them
        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                squares[row][col] = new Square(row, col);

                if ((row == 1)){
                    piece_tag =  "P" + (col + 1) + "B";
                    Piece pawn_black = new Pawn("BLACK",piece_tag);
                    squares[row][col].setOccupiedBy(pawn_black); // Corrected line
                    squares[row][col].setOccupied(true);
                }

                if (row == 6){
                    piece_tag =  "P" + (col + 1) + "W";
                    Piece pawn_white = new Pawn("WHITE",piece_tag);
                    squares[row][col].setOccupiedBy(pawn_white);
                    squares[row][col].setOccupied(true);
                }

                if (row == 0 && (col == 0 || col == 7)){
                    piece_tag = "R" + (col + 1) + "B";
                    Piece rook_black = new Rook("BLACK",piece_tag);
                    squares[row][col].setOccupiedBy(rook_black);
                    squares[row][col].setOccupied(true);
                }

                if (row == 7&& (col == 0 || col == 7)){
                    piece_tag = "R" + (col + 1) + "W";
                    Piece rook_black = new Rook("WHITE",piece_tag);
                    squares[row][col].setOccupiedBy(rook_black);
                    squares[row][col].setOccupied(true);
                }
            }
        }

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
