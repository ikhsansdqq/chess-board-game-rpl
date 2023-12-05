package com.chess.chess_board_game_rpl;

public class Rook extends Piece{
    public Rook(String color, String piece_tag) {
        super(color, piece_tag);
    }
    @Override
    public boolean validMove(Square currentSquare, Square targetSquare,GameBoard gameBoard) {
        // Check for horizontal or vertical move
        boolean isHorizontalMove = currentSquare.getYPosition() == targetSquare.getYPosition();
        boolean isVerticalMove = currentSquare.getXPosition() == targetSquare.getXPosition();

        // Rook must move either horizontally or vertically
        if (!isHorizontalMove && !isVerticalMove) {
            return false;
        }

        // Check for obstacles along the path
        if (isHorizontalMove) {
            int startY = Math.min(currentSquare.getXPosition(), targetSquare.getXPosition());
            int endY = Math.max(currentSquare.getXPosition(), targetSquare.getXPosition());
            for (int x = startY + 1; x < endY; x++) {
                if (gameBoard.getSquare(x, currentSquare.getYPosition()).isOccupied()) {
                    return false; // Path is blocked
                }
            }
        } else if (isVerticalMove) {
            int startX = Math.min(currentSquare.getYPosition(), targetSquare.getYPosition());
            int endX = Math.max(currentSquare.getYPosition(), targetSquare.getYPosition());
            for (int y = startX + 1; y < endX; y++) {
                if (gameBoard.getSquare(currentSquare.getXPosition(), y).isOccupied()) {
                    return false; // Path is blocked
                }
            }
        }

        // Check if the target square is occupied by a piece of the same color
        if (targetSquare.isOccupied() && targetSquare.getOccupiedBy().getColor().equals(currentSquare.getOccupiedBy().getColor())) {
            return false; // Cannot capture your own piece
        }


        return true; // The move is valid
    }

}
