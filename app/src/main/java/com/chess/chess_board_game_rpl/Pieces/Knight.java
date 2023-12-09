package com.chess.chess_board_game_rpl.Pieces;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;

public class Knight extends Piece {
    public Knight(String color, String piece_tag) {
        super(color, piece_tag);
    }

    @Override
    public boolean validMove(Square currentSquare, Square targetSquare, GameBoard gameBoard) {
        int deltaX = Math.abs(targetSquare.getXPosition() - currentSquare.getXPosition());
        int deltaY = Math.abs(targetSquare.getYPosition() - currentSquare.getYPosition());

        // Check if the move is L-shaped (2 squares in one direction and 1 square in the other)
        if (!((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2))) {
            return false;
        }

        return true; // The move is valid
    }
}
