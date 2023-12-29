package com.chess.chess_board_game_rpl.historyTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.chess.chess_board_game_rpl.history.ChessDatabaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ChessDatabaseHelperTest {

    private ChessDatabaseHelper dbHelper;
    private Context context;

    @Before
    public void setUp() {
        // Use InstrumentationRegistry to get the application context
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
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

    // Additional tests can be added here
}

