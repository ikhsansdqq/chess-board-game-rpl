package com.chess.chess_board_game_rpl.history;

public class ChessGame {

    private String movesPlayer1, movesPlayer2;

    public ChessGame(String movesPlayer1, String movesPlayer2) {
        this.movesPlayer1 = movesPlayer1;
        this.movesPlayer2 = movesPlayer2;
    }

    public String getMovesPlayer1() {
        return movesPlayer1;
    }

    public String getMovesPlayer2() {
        return movesPlayer2;
    }
}
