package com.chess.chess_board_game_rpl.Initializer;

import com.chess.chess_board_game_rpl.pieces.Piece;

public class Square {
    private int xPosition;
    private int yPosition;
    private Piece occupiedBy; // This will be a reference to the piece that occupies the square
    private boolean isOccupied;

    public Square(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
        this.occupiedBy = null;
        this.isOccupied = false;// Initially, the square is not occupied
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }
    public void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }

    public int getXPosition(){
        return this.xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public Piece getOccupiedBy(){
        return this.occupiedBy;
    }

    public void setOccupiedBy(Piece occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
}
