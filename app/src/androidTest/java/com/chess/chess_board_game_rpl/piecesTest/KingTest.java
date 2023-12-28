package com.chess.chess_board_game_rpl.piecesTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;
import com.chess.chess_board_game_rpl.pieces.King;

import org.junit.Before;
import org.junit.Test;

public class KingTest {

    private GameBoard gameBoard;
    private King king;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(); // Assuming this initializes an 8x8 board
        king = new King("WHITE", "KING_WHITE");
    }

    @Test
    public void testValidMove() {
        Square currentSquare = new Square(4, 4); // Assuming this creates a square at row 4, column 4
        currentSquare.setOccupiedBy(king);

        // Valid move (one square away)
        Square targetSquare = new Square(5, 4);
        assertTrue("King should be able to move one square", king.validMove(currentSquare, targetSquare, gameBoard));

        // Invalid move (more than one square)
        Square farSquare = new Square(6, 4);
        assertFalse("King should not be able to move more than one square", king.validMove(currentSquare, farSquare, gameBoard));
    }



}

