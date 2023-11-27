package com.chess.chess_board_game_rpl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Rook extends Piece{
    public Rook(String color, String piece_tag) {
        super(color, piece_tag);
    }
    @Override
    public boolean validMove(Square currentSquare, Square targetSquare, Context context) {
        return true;
    }

}
