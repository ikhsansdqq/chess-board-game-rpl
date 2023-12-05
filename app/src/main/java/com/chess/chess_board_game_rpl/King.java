package com.chess.chess_board_game_rpl;

import android.util.Log;

public class King extends Piece{
    public King(String color, String piece_tag) {
        super(color, piece_tag);
    }

    @Override
    public boolean validMove(Square currentSquare, Square targetSquare, GameBoard gameBoard) {
        int deltaX = Math.abs(targetSquare.getXPosition() - currentSquare.getXPosition());
        int deltaY = Math.abs(targetSquare.getYPosition() - currentSquare.getYPosition());

        // King moves only one square in any direction
        if (deltaX > 1 || deltaY > 1) {
            return false;
        }

        return true; // The move is valid
    }


    public static boolean isKingInCheck(GameBoard gameBoard, String kingColor) {
        Square kingSquare = gameBoard.getKingSquare(kingColor);
        String opponentColor = oppositeColor(kingColor);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = gameBoard.getSquare(row, col);
                Piece piece = square.getOccupiedBy();
                if (piece != null && piece.getColor().equals(opponentColor)) {
                    if (piece.validMove(square, kingSquare, gameBoard)) {
                        return true; // King is in check
                    }
                }
            }
        }
        return false;
    }

    private static String oppositeColor(String color) {
        return color.equals("WHITE") ? "BLACK" : "WHITE";
    }

    public static boolean isCheckmate(GameBoard gameBoard, String kingColor) {
        boolean kingCheckMate = true;
        Square kingSquare = gameBoard.getKingSquare(kingColor);
        int kingRow = kingSquare.getXPosition();
        int kingCol = kingSquare.getYPosition();
        King king = (King) gameBoard.getSquare(kingRow,kingCol).getOccupiedBy();

        // Check if King can escape
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                Square newSquare = gameBoard.getSquare(kingSquare.getXPosition() + dx, kingSquare.getYPosition() + dy);
                if (newSquare != null){
                    if (king.validMove(kingSquare, newSquare, gameBoard) && !King.isKingInCheckAfterMove(gameBoard, kingColor, kingSquare, newSquare)) {
                        Log.d("ChessDebug","KING CAN ESCAPE REEEEEEEEEEE");
                        kingCheckMate = false; // King can escape
                    }
                }
            }
        }

        return kingCheckMate; // No legal moves available, checkmate
    }

    public static boolean isKingInCheckAfterMove(GameBoard gameBoard, String kingColor, Square fromSquare, Square toSquare) {
        // Simulate the move
        Piece originalPiece = toSquare.getOccupiedBy();
        toSquare.setOccupiedBy(fromSquare.getOccupiedBy());
        fromSquare.setOccupiedBy(null);

        boolean isInCheck = isKingInCheck(gameBoard, kingColor);

        // Revert the move
        fromSquare.setOccupiedBy(toSquare.getOccupiedBy());
        toSquare.setOccupiedBy(originalPiece);

        return isInCheck;
    }



}
