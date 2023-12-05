package com.chess.chess_board_game_rpl;

public class GameBoard {

    private Square whiteKingSquare;
    private Square blackKingSquare;
    private static Square[][] squares = new Square[8][8];

    private String currentPlayer;

    public GameBoard() {
        this.currentPlayer = "WHITE";
        initializeBoard();
    }
    // Method to switch turns
    public void switchTurn() {
       this.currentPlayer = currentPlayer.equals("WHITE") ? "BLACK" : "WHITE";
    }

    // Method to get the current player
    public String getCurrentPlayer() {
        return currentPlayer;
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
                } else if (row == 0 && (col == 3)) {
                    addPieceToSquare(row, col, "KG", "BLACK", squares);
                } else if (row == 7 && (col == 4)) {
                    addPieceToSquare(row, col, "KG", "WHITE", squares);
                } else if (row == 0 && (col == 4)) {
                    addPieceToSquare(row, col, "QN", "BLACK", squares);
                } else if (row == 7 && (col == 3)) {
                    addPieceToSquare(row, col, "QN", "WHITE", squares);
                }
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
                piece = new Bishop(color,pieceTag);
                break;
            case "KG" :
                piece = new King(color,pieceTag);
                break;
            case "QN" :
                piece = new Queen(color,pieceTag);
                break;
            default:
                return; // or throw an exception
        }

        squares[row][col].setOccupiedBy(piece);
        squares[row][col].setOccupied(true);
    }

    public Square getSquare(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return squares[x][y];
        } else {
            return null; // or throw an exception if you prefer
        }
    }

    public void setKingSquare(String color, Square kingSquare) {
        if (color.equals("WHITE")) {
            whiteKingSquare = kingSquare;
        } else if (color.equals("BLACK")) {
            blackKingSquare = kingSquare;
        }
    }

    public Square getKingSquare(String color) {
        return color.equals("WHITE") ? whiteKingSquare : blackKingSquare;
    }
}
