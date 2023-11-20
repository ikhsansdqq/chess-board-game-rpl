package com.chess.chess_board_game_rpl;

public class Pawn extends Piece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean validMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Pawns can move forward one square, but cannot move backwards.
        // White pawns will be moving 'up' the board (decreasing y value), and black pawns 'down' (increasing y value).
        int direction = this.color.equals("white") ? -1 : 1;
        int startRow = this.color.equals("white") ? 6 : 1;  // Starting row for white is 6, for black is 1

        // Move forward one square
        if (startX == endX && startY + direction == endY && board[endX][endY] == null) {
            return true;
        }

        // Capture diagonally
        if (Math.abs(startX - endX) == 1 && startY + direction == endY && board[endX][endY] != null &&
                !board[endX][endY].getColor().equals(this.color)) {
            return true;
        }

        // First move can be two squares forward
        if (startX == endX && !hasMoved && startY + (2 * direction) == endY &&
                board[endX][endY] == null && board[startX][startY + direction] == null) {
            return true;
        }

        // Add more rules if needed, such as en passant, promotion, etc.

        return false;
    }
}

