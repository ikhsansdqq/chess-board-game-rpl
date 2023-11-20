package com.chess.chess_board_game_rpl;

public abstract class Piece {
    protected String color;
    protected boolean hasMoved;

    public Piece(String color) {
        this.color = color;
        this.hasMoved = false;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public String getColor() {
        return color;
    }

    // Abstract method to be implemented by all concrete Piece classes
    public abstract boolean validMove(int startX, int startY, int endX, int endY, Piece[][] board);
}
