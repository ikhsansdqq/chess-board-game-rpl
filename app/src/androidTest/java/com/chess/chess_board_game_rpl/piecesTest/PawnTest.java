package com.chess.chess_board_game_rpl.piecesTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;
import com.chess.chess_board_game_rpl.pieces.Pawn;

import org.junit.Before;
import org.junit.Test;

public class PawnTest {

    private GameBoard gameBoard;
    private Pawn pawn;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(); // Initialize your game board
        pawn = new Pawn("WHITE", "P1"); // Initialize a Pawn
    }

    @Test
    public void testFirstMoveTwoSquares() {
        // Assume the pawn is placed at (6, 0)
        Square currentSquare = gameBoard.getSquare(6, 0);
        currentSquare.setOccupiedBy(pawn);

        // Valid first move - two squares forward
        Square targetSquare = gameBoard.getSquare(4, 0);
        assertTrue("Pawn should be able to move two squares on its first move", pawn.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testMoveOneSquare() {
        // Set the pawn as having moved
        pawn.setFirstMove(false);

        // Assume the pawn is placed at (5, 0)
        Square currentSquare = gameBoard.getSquare(5, 0);
        currentSquare.setOccupiedBy(pawn);

        // Valid move - one square forward
        Square targetSquare = gameBoard.getSquare(4, 0);
        assertTrue("Pawn should be able to move one square forward", pawn.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testInvalidMove() {
        // Assume the pawn is placed at (6, 0)
        Square currentSquare = gameBoard.getSquare(6, 0);
        currentSquare.setOccupiedBy(pawn);

        // Invalid move - sideways
        Square targetSquare = gameBoard.getSquare(6, 1);
        assertFalse("Pawn should not be able to move sideways", pawn.validMove(currentSquare, targetSquare, gameBoard));
    }

    @Test
    public void testCaptureMove() {
        // Assume the pawn is placed at (6, 0)
        Square currentSquare = gameBoard.getSquare(6, 0);
        currentSquare.setOccupiedBy(pawn);

        // Assume an enemy piece is diagonally in front of the pawn
        Square captureSquare = gameBoard.getSquare(5, 1);
        captureSquare.setOccupiedBy(new Pawn("BLACK", "P2"));

        // Valid capture move
        assertTrue("Pawn should be able to capture diagonally", pawn.validMove(currentSquare, captureSquare, gameBoard));
    }

}