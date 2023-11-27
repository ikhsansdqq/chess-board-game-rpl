package com.chess.chess_board_game_rpl;

import android.util.Log;
import android.widget.Toast;
import android.content.Context;

public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(String color, String piece_tag) {
        super(color, piece_tag);
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isFirstMove() {
        return this.firstMove;
    }

    @Override
    public boolean validMove(Square currentSquare, Square targetSquare,Context context) {

        boolean valid = false;
        int deltaX = targetSquare.getXPosition() - currentSquare.getXPosition();
        int deltaY = targetSquare.getYPosition() - currentSquare.getYPosition();

        if (currentSquare.getOccupiedBy() instanceof Pawn) {

            Pawn pawn = (Pawn) currentSquare.getOccupiedBy();
            // Assuming white pawns move up (decreasing X) and black pawns move down (increasing X)
            int direction = currentSquare.getOccupiedBy().getColor().equals("WHITE") ? -1 : 1;

            // Check if moving forward to an empty square
            if (deltaX == direction && deltaY == 0 && targetSquare.getOccupiedBy() == null) {
                valid = true;
            }
            // Check if it's the first move and moving two squares forward
            else if (deltaX == 2 * direction && deltaY == 0 && pawn.isFirstMove() && targetSquare.getOccupiedBy() == null) {
                Log.d("ChessDebug", String.valueOf(deltaX));
                // Also check if the path is clear (no piece on the square in between)
               valid = true;
            }
            // Check for diagonal capture
            else if (deltaX == direction && Math.abs(deltaY) == 1 && targetSquare.getOccupiedBy() != null && !targetSquare.getOccupiedBy().getColor().equals(currentSquare.getOccupiedBy().getColor())) {
                valid = true;
                Toast.makeText(context, "Captured SOMEONE " + targetSquare.getOccupiedBy().getPiece_tag(), Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }
}

