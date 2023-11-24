package com.chess.chess_board_game_rpl;

public class Pawn extends Piece {

    public Pawn(String color) {
        super(color);
    }



    @Override
    public boolean validMove(Square square, Square clickedSquare) {
        return true;
    }
}

