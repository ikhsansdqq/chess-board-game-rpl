package com.chess.chess_board_game_rpl.piecesTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;
import com.chess.chess_board_game_rpl.pieces.Knight;

import org.junit.Before;
import org.junit.Test;

public class KnightTest {

    private GameBoard gameBoard;
    private Knight knight;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(); // Initialize your game board
        knight = new Knight("WHITE", "N1"); // Initialize a Knight
    }

    @Test
    public void testValidKnightMove() {
        // Assume the knight is placed at (1, 0)
        Square currentSquare = gameBoard.getSquare(1, 0);
        currentSquare.setOccupiedBy(knight);

        // Valid move for a knight (L-shape move)
        Square targetSquare = gameBoard.getSquare(2, 2);
        assertTrue("Knight should be able to make a valid L-shaped move", knight.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testInvalidMove() {
        // Assume the knight is placed at (1, 0)
        Square currentSquare = gameBoard.getSquare(1, 0);
        currentSquare.setOccupiedBy(knight);

        // Invalid move (not L-shaped)
        Square targetSquare = gameBoard.getSquare(2, 1);
        assertFalse("Knight should not be able to move non-L-shaped", knight.validMove(currentSquare, targetSquare, gameBoard));
    }

}