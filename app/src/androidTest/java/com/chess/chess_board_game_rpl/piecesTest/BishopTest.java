package com.chess.chess_board_game_rpl.piecesTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;
import com.chess.chess_board_game_rpl.pieces.Bishop;
import com.chess.chess_board_game_rpl.pieces.Pawn;

import org.junit.Before;
import org.junit.Test;

public class BishopTest {

    private GameBoard gameBoard;
    private Bishop bishop;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(); // Initialize your game board
        bishop = new Bishop("WHITE", "B1"); // Initialize a Bishop
    }

    @Test
    public void testValidDiagonalMove() {
        // Assume the bishop is placed at (2, 0) - change as per your board setup
        Square currentSquare = gameBoard.getSquare(2, 0);
        currentSquare.setOccupiedBy(bishop);

        // Valid move to (5, 3)
        Square targetSquare = gameBoard.getSquare(5, 3);
        assertTrue("Bishop should be able to move diagonally", bishop.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testInvalidMove() {
        // Assume the bishop is placed at (2, 0)
        Square currentSquare = gameBoard.getSquare(2, 0);
        currentSquare.setOccupiedBy(bishop);

        // Invalid move to a non-diagonal square (4, 1)
        Square targetSquare = gameBoard.getSquare(4, 1);
        assertFalse("Bishop should not be able to move non-diagonally", bishop.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testMoveWithObstacle() {
        // Assume the bishop is placed at (2, 0) and there's an obstacle at (3, 1)
        Square currentSquare = gameBoard.getSquare(2, 0);
        currentSquare.setOccupiedBy(bishop);

        Square obstacleSquare = gameBoard.getSquare(3, 1);
        obstacleSquare.setOccupiedBy(new Pawn("BLACK", "P1")); // Place an obstacle

        // Move to (5, 3) is blocked by the obstacle
        Square targetSquare = gameBoard.getSquare(5, 3);
        assertFalse("Bishop should not be able to move past an obstacle", bishop.validMove(currentSquare, targetSquare, gameBoard));
    }

}
