package com.chess.chess_board_game_rpl;

public class Pawn extends Piece {

    public Pawn(String color, String piece_tag) {
        super(color, piece_tag);
    }


    @Override
    public boolean validMove(Square square, Square clickedSquare) {

        boolean valid = true;
        if (clickedSquare.getOccupiedBy() == null) { //Check if the pieces is the same pieces
            //Log.d("ChessDebug", "VALID MOVE");

        } else if (clickedSquare.getOccupiedBy().getPiece_tag().equals(square.getOccupiedBy().getPiece_tag())) {
            //Log.d("ChessDebug", "INVALID MOVE");
            valid = false;

        }
        return valid;
    }
}

