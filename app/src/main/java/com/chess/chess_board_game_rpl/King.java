package com.chess.chess_board_game_rpl;

import android.content.Context;

public class King extends Piece{
    public King(String color, String piece_tag) {
        super(color, piece_tag);
    }

    @Override
    public boolean validMove(Square square, Square clickedSquare, GameBoard gameBoard, Context context) {
        return true;
    }
}
