package com.chess.chess_board_game_rpl.piecesTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;
import com.chess.chess_board_game_rpl.pieces.Pawn;
import com.chess.chess_board_game_rpl.pieces.Rook;

import org.junit.Before;
import org.junit.Test;

public class RookTest {

    private GameBoard gameBoard;
    private Rook rook;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(); // Initialize your game board
        rook = new Rook("WHITE", "R1"); // Initialize a Rook
    }

    @Test
    public void testValidVerticalMove() {
        Square currentSquare = gameBoard.getSquare(2, 2);
        currentSquare.setOccupiedBy(rook);

        // Valid vertical move
        Square targetSquare = gameBoard.getSquare(5, 2);
        assertTrue("Rook should be able to move vertically", rook.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testValidHorizontalMove() {
        Square currentSquare = gameBoard.getSquare(2, 2);
        currentSquare.setOccupiedBy(rook);

        // Valid horizontal move
        Square targetSquare = gameBoard.getSquare(2, 5);
        assertTrue("Rook should be able to move horizontally", rook.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testInvalidMove() {
        Square currentSquare = gameBoard.getSquare(2, 2);
        currentSquare.setOccupiedBy(rook);

        // Invalid move (not along rank or file)
        Square targetSquare = gameBoard.getSquare(4, 5);
        assertFalse("Rook should not be able to move diagonally", rook.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testMoveWithObstacle() {
        // Assume the rook is placed at (2, 2) and there's an obstacle at (3, 2)
        Square currentSquare = gameBoard.getSquare(2, 2);
        currentSquare.setOccupiedBy(rook);

        Square obstacleSquare = gameBoard.getSquare(3, 2);
        obstacleSquare.setOccupiedBy(new Pawn("BLACK", "P1")); // Place an obstacle

        // Move to (5, 2) is blocked by the obstacle
        Square targetSquare = gameBoard.getSquare(5, 2);
        assertFalse("Rook should not be able to move past an obstacle", rook.validMove(currentSquare, targetSquare, gameBoard));
    }

}
