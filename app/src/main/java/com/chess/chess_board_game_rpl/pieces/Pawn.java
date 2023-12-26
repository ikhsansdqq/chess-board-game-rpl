package com.chess.chess_board_game_rpl.pieces;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;

import java.util.Objects;

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
    public boolean validMove(Square currentSquare, Square targetSquare, GameBoard gameBoard) {

        boolean valid = false;
        int deltaX = targetSquare.getXPosition() - currentSquare.getXPosition();
        int deltaY = targetSquare.getYPosition() - currentSquare.getYPosition();

        if (currentSquare.getOccupiedBy() instanceof Pawn) {

            Pawn pawn = (Pawn) currentSquare.getOccupiedBy();
            // White pawns move up (decreasing X) and black pawns move down (increasing X)
            int direction = currentSquare.getOccupiedBy().getColor().equals("WHITE") ? -1 : 1;

            // Check if moving forward to an empty square
            if (deltaX == direction && deltaY == 0 && (targetSquare.getOccupiedBy() == null || !Objects.equals(targetSquare.getOccupiedBy().getColor(), pawn.getColor()))) {
                valid = true;

            }
            // Check if it's the first move and moving two squares forward
            else if (deltaX == 2 * direction && deltaY == 0 && pawn.isFirstMove() && targetSquare.getOccupiedBy() == null) {
                // Also check if the path is clear (no piece on the square in between)
               valid = true;
            }
            // Check for diagonal capture
            else if (deltaX == direction && Math.abs(deltaY) == 1 && targetSquare.getOccupiedBy() != null && !targetSquare.getOccupiedBy().getColor().equals(currentSquare.getOccupiedBy().getColor())) {
                valid = true;
            }
        }
        return valid;
    }
}

