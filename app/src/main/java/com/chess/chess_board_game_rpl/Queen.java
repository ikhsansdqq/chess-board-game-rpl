package com.chess.chess_board_game_rpl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Queen extends Piece {

    public Queen(String color, String piece_tag) {
        super(color, piece_tag);
    }

    @Override
    public boolean validMove(Square currentSquare, Square targetSquare, GameBoard gameBoard, Context context) {
        // Check for horizontal or vertical move
        boolean isHorizontalMove = currentSquare.getYPosition() == targetSquare.getYPosition();
        boolean isVerticalMove = currentSquare.getXPosition() == targetSquare.getXPosition();
        int deltaX = Math.abs(targetSquare.getXPosition() - currentSquare.getXPosition());
        int deltaY = Math.abs(targetSquare.getYPosition() - currentSquare.getYPosition());

        // Queen must move either horizontally or vertically or diagonal
        if (!isHorizontalMove && !isVerticalMove && (deltaX != deltaY)) {
            return false;
        }

        // Check for obstacles along the path
        if (isHorizontalMove) {
            int startY = Math.min(currentSquare.getXPosition(), targetSquare.getXPosition());
            int endY = Math.max(currentSquare.getXPosition(), targetSquare.getXPosition());
            for (int x = startY + 1; x < endY; x++) {
                if (gameBoard.getSquare(x, currentSquare.getYPosition()).isOccupied() ) {
                    return false; // Path is blocked
                }
            }
        } else if (isVerticalMove) {
            int startX = Math.min(currentSquare.getYPosition(), targetSquare.getYPosition());
            int endX = Math.max(currentSquare.getYPosition(), targetSquare.getYPosition());
            for (int y = startX + 1; y < endX; y++) {
                if (gameBoard.getSquare(currentSquare.getXPosition(), y).isOccupied()) {
                    return false; // Path is blocked
                }
            }
        } else if(deltaX == deltaY){
            // Check for obstacles along the diagonal path
            int startX = currentSquare.getXPosition();
            int startY = currentSquare.getYPosition();
            int endX = targetSquare.getXPosition();
            int endY = targetSquare.getYPosition();

            int stepX = (endX > startX) ? 1 : -1; // Determine the direction of movement along the X-axis
            int stepY = (endY > startY) ? 1 : -1; // Determine the direction of movement along the Y-axis

            for (int x = startX + stepX, y = startY + stepY; x != endX; x += stepX, y += stepY) {
                if (gameBoard.getSquare(x, y).isOccupied()) {
                    Log.d("ChessDebug","PATH IS BLOCKED REEEEEE");
                    return false; // Path is blocked
                }
            }
        }

        // Check if the target square is occupied by a piece of the same color
        if (targetSquare.isOccupied() && targetSquare.getOccupiedBy().getColor().equals(currentSquare.getOccupiedBy().getColor())) {
            return false; // Cannot capture your own piece
        }

        // If capturing, show a Toast message
        if (targetSquare.isOccupied() && !targetSquare.getOccupiedBy().getColor().equals(currentSquare.getOccupiedBy().getColor())) {
            Toast.makeText(context, "Queen captured " + targetSquare.getOccupiedBy().getPiece_tag(), Toast.LENGTH_SHORT).show();
        }

        return true; // The move is valid

    }

}
