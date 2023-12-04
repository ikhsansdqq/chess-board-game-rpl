package com.chess.chess_board_game_rpl;

import android.content.Context;
import android.widget.Toast;

public class Bishop extends Piece{

    public Bishop(String color, String piece_tag) {
        super(color, piece_tag);
    }

    @Override
    public boolean validMove(Square currentSquare, Square targetSquare, GameBoard gameBoard, Context context) {
        int deltaX = Math.abs(targetSquare.getXPosition() - currentSquare.getXPosition());
        int deltaY = Math.abs(targetSquare.getYPosition() - currentSquare.getYPosition());

        // Bishops move diagonally, so the absolute values of deltaX and deltaY must be equal
        if (deltaX != deltaY) {
            return false;
        }

        // Check for obstacles along the diagonal path
        int startX = currentSquare.getXPosition();
        int startY = currentSquare.getYPosition();
        int endX = targetSquare.getXPosition();
        int endY = targetSquare.getYPosition();

        int stepX = (endX > startX) ? 1 : -1; // Determine the direction of movement along the X-axis
        int stepY = (endY > startY) ? 1 : -1; // Determine the direction of movement along the Y-axis

        for (int x = startX + stepX, y = startY + stepY; x != endX; x += stepX, y += stepY) {
            if (gameBoard.getSquare(x, y).isOccupied()) {
                return false; // Path is blocked
            }
        }

        // Check if the target square is occupied by a piece of the same color
        if (targetSquare.isOccupied() && targetSquare.getOccupiedBy().getColor().equals(currentSquare.getOccupiedBy().getColor())) {
            return false; // Cannot capture your own piece
        }

        // If capturing, show a Toast message
        if (targetSquare.isOccupied() && !targetSquare.getOccupiedBy().getColor().equals(currentSquare.getOccupiedBy().getColor())) {
            Toast.makeText(context, "Bishop captured " + targetSquare.getOccupiedBy().getPiece_tag(), Toast.LENGTH_SHORT).show();
        }

        return true; // The move is valid
    }


}
