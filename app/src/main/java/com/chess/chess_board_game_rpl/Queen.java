package com.chess.chess_board_game_rpl;

import android.content.Context;
import android.widget.Toast;

public class Queen extends Piece {

    public Queen(String color, String piece_tag) {
        super(color, piece_tag);
    }

    @Override
    public boolean validMove(Square currentSquare, Square targetSquare, GameBoard gameBoard, Context context) {
        int deltaX = targetSquare.getXPosition() - currentSquare.getXPosition();
        int deltaY = targetSquare.getYPosition() - currentSquare.getYPosition();

        boolean isHorizontalMove = deltaY == 0;
        boolean isVerticalMove = deltaX == 0;
        boolean isDiagonalMove = Math.abs(deltaX) == Math.abs(deltaY);

        // Queen must move either horizontally, vertically, or diagonally
        if (!(isHorizontalMove || isVerticalMove || isDiagonalMove)) {
            return false;
        }

        // Check for obstacles along the path
        int stepX = Integer.compare(targetSquare.getXPosition(), currentSquare.getXPosition());
        int stepY = Integer.compare(targetSquare.getYPosition(), currentSquare.getYPosition());

        int x = currentSquare.getXPosition() + stepX;
        int y = currentSquare.getYPosition() + stepY;

        while (x != targetSquare.getXPosition() || y != targetSquare.getYPosition()) {
            if (gameBoard.getSquare(currentSquare.getXPosition(), y).isOccupied()) {
                return false; // Path is blocked
            }
            x += stepX;
            y += stepY;
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
