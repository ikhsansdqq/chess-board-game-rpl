package com.chess.chess_board_game_rpl.piecesTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;
import com.chess.chess_board_game_rpl.pieces.Queen;

import org.junit.Before;
import org.junit.Test;

public class QueenTest {

    private GameBoard gameBoard;
    private Queen queen;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(); // Initialize your game board
        queen = new Queen("WHITE", "Q1"); // Initialize a Queen
    }

    @Test
    public void testValidVerticalMove() {
        Square currentSquare = gameBoard.getSquare(2, 2);
        currentSquare.setOccupiedBy(queen);

        // Valid vertical move
        Square targetSquare = gameBoard.getSquare(5, 2);
        assertTrue("Queen should be able to move vertically", queen.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testValidHorizontalMove() {
        Square currentSquare = gameBoard.getSquare(2, 2);
        currentSquare.setOccupiedBy(queen);

        // Valid horizontal move
        Square targetSquare = gameBoard.getSquare(2, 5);
        assertTrue("Queen should be able to move horizontally", queen.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testValidDiagonalMove() {
        Square currentSquare = gameBoard.getSquare(2, 2);
        currentSquare.setOccupiedBy(queen);

        // Valid diagonal move
        Square targetSquare = gameBoard.getSquare(4, 4);
        assertTrue("Queen should be able to move diagonally", queen.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testInvalidMove() {
        Square currentSquare = gameBoard.getSquare(2, 2);
        currentSquare.setOccupiedBy(queen);

        // Invalid move (not along rank, file, or diagonal)
        Square targetSquare = gameBoard.getSquare(4, 5);
        assertFalse("Queen should not be able to move in this pattern", queen.validMove(currentSquare, targetSquare, gameBoard));
    }

    // Additional tests can be added for moves with obstacles...
}
