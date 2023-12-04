package com.chess.chess_board_game_rpl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class King extends Piece{
    public King(String color, String piece_tag) {
        super(color, piece_tag);
    }

    @Override
    public boolean validMove(Square currentSquare, Square targetSquare, GameBoard gameBoard, Context context) {
        int deltaX = Math.abs(targetSquare.getXPosition() - currentSquare.getXPosition());
        int deltaY = Math.abs(targetSquare.getYPosition() - currentSquare.getYPosition());

        // King moves only one square in any direction
        if (deltaX > 1 || deltaY > 1) {
            return false;
        }

        // Check if the target square is occupied by a piece of the same color
        if (targetSquare.isOccupied() && targetSquare.getOccupiedBy().getColor().equals(currentSquare.getOccupiedBy().getColor())) {
            return false; // Cannot capture your own piece
        }

        // If capturing, show a Toast message
        if (targetSquare.isOccupied() && !targetSquare.getOccupiedBy().getColor().equals(currentSquare.getOccupiedBy().getColor())) {
            Toast.makeText(context, "King captured " + targetSquare.getOccupiedBy().getPiece_tag(), Toast.LENGTH_SHORT).show();
        }

        return true; // The move is valid
    }


    public static boolean isKingInCheck(GameBoard gameBoard, String kingColor) {
        Square kingSquare = gameBoard.getKingSquare(kingColor);
        if (kingSquare == null) return false; // King not found

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = gameBoard.getSquare(row, col);
                Piece piece = square.getOccupiedBy();
                if (piece != null && !piece.getColor().equals(kingColor)) {
                    if (piece.validMove(square, kingSquare, gameBoard, null)) {
                        return true; // King is in check
                    }
                }
            }
        }
        return false;
    }

    public static boolean isCheckmate(GameBoard gameBoard, String kingColor) {


        if (!isKingInCheck(gameBoard, kingColor)) {
            return false;
        }
        boolean kingCheckMate = true;

        Square kingSquare = gameBoard.getKingSquare(kingColor);
        King king = (King) kingSquare.getOccupiedBy();

        // Check if King can escape
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                Square newSquare = gameBoard.getSquare(kingSquare.getXPosition() + dx, kingSquare.getYPosition() + dy);
                if (newSquare != null && king.validMove(kingSquare, newSquare, gameBoard, null) && !King.isKingInCheckAfterMove(gameBoard, kingColor, kingSquare, newSquare)) {
                    kingCheckMate = false; // King can escape
                }
            }
        }

        // Additional checks for other pieces to block or capture are needed here
        // ...
        Log.d("ChessDebug", String.valueOf(kingCheckMate));
        return kingCheckMate; // No legal moves available, checkmate
    }

    public static boolean isKingInCheckAfterMove(GameBoard gameBoard, String kingColor, Square currentKingSquare, Square newKingSquare) {
        // Remember the original state
        Piece originalPieceAtNewSquare = newKingSquare.getOccupiedBy();

        // Simulate the move
        newKingSquare.setOccupiedBy(currentKingSquare.getOccupiedBy());
        currentKingSquare.setOccupiedBy(null);

        // Check if the King is in check after the move
        boolean isInCheck = isKingInCheck(gameBoard, kingColor);

        // Revert the move
        currentKingSquare.setOccupiedBy(newKingSquare.getOccupiedBy());
        newKingSquare.setOccupiedBy(originalPieceAtNewSquare);

        return isInCheck;
    }


}
