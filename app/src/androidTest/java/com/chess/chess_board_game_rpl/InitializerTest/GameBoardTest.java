package com.chess.chess_board_game_rpl.InitializerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.chess.chess_board_game_rpl.Initializer.GameBoard;
import com.chess.chess_board_game_rpl.Initializer.Square;
import com.chess.chess_board_game_rpl.pieces.Bishop;
import com.chess.chess_board_game_rpl.pieces.King;
import com.chess.chess_board_game_rpl.pieces.Knight;
import com.chess.chess_board_game_rpl.pieces.Pawn;
import com.chess.chess_board_game_rpl.pieces.Queen;
import com.chess.chess_board_game_rpl.pieces.Rook;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
    private GameBoard gameBoard;
    @Before
    public void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    public void testBoardInitialization() {
        // Test if all squares are initialized correctly
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = gameBoard.getSquare(row, col);
                assertNotNull("Square should not be null", square);

                // Check if pieces are placed correctly based on your initial setup
                if (row == 1) {
                    assertTrue("Square should be occupied by a Black Pawn", square.isOccupied() && square.getOccupiedBy() instanceof Pawn && square.getOccupiedBy().getColor().equals("BLACK"));
                } else if (row == 6) {
                    assertTrue("Square should be occupied by a White Pawn", square.isOccupied() && square.getOccupiedBy() instanceof Pawn && square.getOccupiedBy().getColor().equals("WHITE"));
                } else if (row == 0 && (col == 0 || col == 7)) {
                    assertTrue("Square should be occupied by a Black Rook", square.isOccupied() && square.getOccupiedBy() instanceof Rook && square.getOccupiedBy().getColor().equals("BLACK"));
                } else if (row == 7 && (col == 0 || col == 7)) {
                    assertTrue("Square should be occupied by a White Rook", square.isOccupied() && square.getOccupiedBy() instanceof Rook && square.getOccupiedBy().getColor().equals("WHITE"));
                } else if (row == 0 && (col == 1 || col == 6)) {
                    assertTrue("Square should be occupied by a Knight Black", square.isOccupied() && square.getOccupiedBy() instanceof Knight && square.getOccupiedBy().getColor().equals("BLACK"));
                } else if (row == 7 && (col == 1 || col == 6)) {
                    assertTrue("Square should be occupied by a Knight White", square.isOccupied() && square.getOccupiedBy() instanceof Knight && square.getOccupiedBy().getColor().equals("WHITE"));
                } else if (row == 0 && (col == 2 || col == 5)) {
                    assertTrue("Square should be occupied by a Bishop Black", square.isOccupied() && square.getOccupiedBy() instanceof Bishop && square.getOccupiedBy().getColor().equals("BLACK"));
                } else if (row == 7 && (col == 2 || col == 5)) {
                    assertTrue("Square should be occupied by a Bishop White", square.isOccupied() && square.getOccupiedBy() instanceof Bishop && square.getOccupiedBy().getColor().equals("WHITE"));
                } else if (row == 0 && (col == 3)) {
                    assertTrue("Square should be occupied by a King Black", square.isOccupied() && square.getOccupiedBy() instanceof King && square.getOccupiedBy().getColor().equals("BLACK"));
                } else if (row == 7 && (col == 3)) {
                    assertTrue("Square should be occupied by a King White", square.isOccupied() && square.getOccupiedBy() instanceof King && square.getOccupiedBy().getColor().equals("WHITE"));
                } else if (row == 0 && (col == 4)) {
                    assertTrue("Square should be occupied by a Queen Black", square.isOccupied() && square.getOccupiedBy() instanceof Queen && square.getOccupiedBy().getColor().equals("BLACK"));
                } else if (row == 7 && (col == 4)) {
                    assertTrue("Square should be occupied by a Queen White", square.isOccupied() && square.getOccupiedBy() instanceof Queen && square.getOccupiedBy().getColor().equals("WHITE"));
                }
            }
        }
    }

    @Test
    public void testSwitchTurn() {
        assertEquals("Initial turn should be WHITE", "WHITE", gameBoard.getCurrentPlayer());
        gameBoard.switchTurn();
        assertEquals("Turn after switch should be BLACK", "BLACK", gameBoard.getCurrentPlayer());
        gameBoard.switchTurn();
        assertEquals("Turn after second switch should be WHITE", "WHITE", gameBoard.getCurrentPlayer());
    }

    @Test
    public void testSetAndGetKingSquare() {
        Square kingSquare = new Square(0, 4);
        gameBoard.setKingSquare("WHITE", kingSquare);
        assertEquals("King square should be set correctly for WHITE", kingSquare, gameBoard.getKingSquare("WHITE"));
    }
}
