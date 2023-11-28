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

                if (row == 1) {
                    addPieceToSquare(row, col, "P", "BLACK", squares);
                } else if (row == 6) {
                    addPieceToSquare(row, col, "P", "WHITE", squares);
                } else if (row == 0 && (col == 0 || col == 7)) {
                    addPieceToSquare(row, col, "R", "BLACK", squares);
                } else if (row == 7 && (col == 0 || col == 7)) {
                    addPieceToSquare(row, col, "R", "WHITE", squares);
                } else if (row == 0 && (col == 1 || col == 6)) {
                    addPieceToSquare(row, col, "K", "BLACK", squares);
                } else if (row == 7 && (col == 1 || col == 6)) {
                    addPieceToSquare(row, col, "K", "WHITE", squares);
                } else if (row == 0 && (col == 2 || col == 5)) {
                    addPieceToSquare(row, col, "B", "BLACK", squares);
                } else if (row == 7 && (col == 2 || col == 5)) {
                    addPieceToSquare(row, col, "B", "WHITE", squares);
              }
                // Add other pieces like bishops, queens, and kings
            }
        }
    }

    private void addPieceToSquare(int row, int col, String type, String color, Square[][] squares) {
        String pieceTag = type + (col + 1) + (color.equals("BLACK") ? "B" : "W");
        Piece piece;

        switch (type) {
            case "P":
                piece = new Pawn(color, pieceTag);
                break;
            case "R":
                piece = new Rook(color, pieceTag);
                break;
            case "K":
                piece = new Knight(color, pieceTag);
                break;
            case "B" :
                piece = new Bishop(color,piece_tag);
                break;
            // Add cases for other piece types
            default:
                return; // or throw an exception
        }

        squares[row][col].setOccupiedBy(piece);
        squares[row][col].setOccupied(true);
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
