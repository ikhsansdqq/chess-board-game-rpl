package com.chess.chess_board_game_rpl.InitializerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import android.util.Log;

import com.chess.chess_board_game_rpl.Initializer.Square;
import com.chess.chess_board_game_rpl.pieces.Piece;

import org.junit.Before;
import org.junit.Test;

public class SquareTest {

    private Square square;
    private Piece mockPiece;
    @Before
    public void setUp() {
        square = new Square(0, 0); // Initialize a square at position (0,0)
        mockPiece = mock(Piece.class); // Create a mock Piece object
    }

    @Test
    public void testInitialSquareState() {
        assertFalse("Square should initially not be occupied", square.isOccupied());
        assertNull("Occupied piece should initially be null", square.getOccupiedBy());
    }

    @Test
    public void testSetOccupiedBy() {
        square.setOccupiedBy(mockPiece);
        Log.d("TestCase", String.valueOf(square.getOccupiedBy()));
        assertTrue("Square should be occupied after setting a piece", square.isOccupied());
        assertEquals("Occupied piece should be the one that was set", mockPiece, square.getOccupiedBy());
    }

    @Test
    public void testClearSquare() {
        square.setOccupiedBy(null);
        assertFalse("Square should not be occupied after clearing the piece", square.isOccupied());
        assertNull("Occupied piece should be null after clearing the square", square.getOccupiedBy());
    }

    @Test
    public void testSquarePosition() {
        assertEquals("X position should match", 0, square.getXPosition());
        assertEquals("Y position should match", 0, square.getYPosition());
    }

}
