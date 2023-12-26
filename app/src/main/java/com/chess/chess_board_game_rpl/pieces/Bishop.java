package com.chess.chess_board_game_rpl.pieces;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;

public class Bishop extends Piece{

    public Bishop(String color, String piece_tag) {
        super(color, piece_tag);
    }

    @Override
    public boolean validMove(Square currentSquare, Square targetSquare, GameBoard gameBoard) {
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

        return true; // The move is valid
    }


}
