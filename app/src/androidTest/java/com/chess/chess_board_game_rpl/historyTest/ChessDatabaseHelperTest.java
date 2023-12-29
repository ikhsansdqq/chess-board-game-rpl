package com.chess.chess_board_game_rpl.historyTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.chess.chess_board_game_rpl.history.ChessDatabaseHelper;
import com.chess.chess_board_game_rpl.history.ChessGame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mock.*;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ChessDatabaseHelperTest {

    private ChessDatabaseHelper dbHelper;

    @Mock
    private SQLiteDatabase mockDatabase;

    @Mock
    private Cursor mockCursor;

    @Before
    public void setUp() {
        // Use InstrumentationRegistry to get the application context
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        context.deleteDatabase(ChessDatabaseHelper.DB_NAME);
        dbHelper = new ChessDatabaseHelper(context);
    }

    @After
    public void finish() {
        dbHelper.close();
    }

    @Test
    public void testAddGame() {
        dbHelper.addGame("Ayaka", "Ayato", "2023-12-29", "1h 30m",
                "e4, d4, Nf3", "e5, Nf6, Bb4",
                "pawn, knight", "pawn, bishop", "Ayaka");

        // Verify that the data was inserted correctly
        List<String> games = dbHelper.getAllGames();
        assertFalse(games.isEmpty());
        assertTrue(games.get(0).contains("Ayaka"));
        assertTrue(games.get(0).contains("Ayato"));
    }

    @Test
    public void testFetchMovesGame() {
        int gameId = 1;
        String expectedMovesPlayer1 = "e4, d4, Nf3";
        String expectedMovesPlayer2 = "e5, Nf6, Bb4";

        when(mockCursor.moveToFirst()).thenReturn(true);
        when(mockCursor.getColumnIndex(ChessDatabaseHelper.COLUMN_MOVES_PLAYER1)).thenReturn(1);
        when(mockCursor.getColumnIndex(ChessDatabaseHelper.COLUMN_MOVES_PLAYER2)).thenReturn(2);

        when(mockCursor.getString(1)).thenReturn(expectedMovesPlayer1);
        when(mockCursor.getString(2)).thenReturn(expectedMovesPlayer2);

        // Act
        ChessGame result = dbHelper.fetchMovesForGame(gameId);

        // Assert
        assertEquals(expectedMovesPlayer1, result.getMovesPlayer1());
        assertEquals(expectedMovesPlayer2, result.getMovesPlayer2());

        // Verify that methods were called as expected
        verify(mockDatabase).close();
        verify(mockCursor).close();
    }

    // Additional tests can be added here
}

