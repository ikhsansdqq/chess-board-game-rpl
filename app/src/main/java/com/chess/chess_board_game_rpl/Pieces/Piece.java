package com.chess.chess_board_game_rpl.Pieces;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;

public abstract class Piece {

    protected String color;
    protected String piece_tag;
    public Piece(String color,String piece_tag) {
        this.color = color;
        this.piece_tag = piece_tag;
    }

    public String getColor() {
        return color;
    }

    public String getPiece_tag() {
        return piece_tag;
    }

    public abstract boolean validMove(Square square, Square clickedSquare, GameBoard gameBoard);
}
