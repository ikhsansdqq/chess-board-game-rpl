package com.chess.chess_board_game_rpl;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.chess.chess_board_game_rpl.Initializer.Square;
import com.chess.chess_board_game_rpl.pieces.Pawn;
import com.chess.chess_board_game_rpl.pieces.Piece;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

@RunWith(AndroidJUnit4.class)

public class InGameActivityTest {

    @Rule
    public ActivityScenarioRule<InGameActivity> activityRule = new ActivityScenarioRule<>(InGameActivity.class);

    @Test
    public void testInitialSetup() {
        activityRule.getScenario().onActivity(activity -> {

            GridLayout chessBoard = activity.findViewById(R.id.chessBoard);
            assertNotNull("Chess board is not initialized", chessBoard);
            assertEquals("Chess board does not have the correct number of children", 64, chessBoard.getChildCount());

            Toolbar toolbar = activity.findViewById(R.id.my_toolbar);
            assertNotNull("Toolbar is not initialized", toolbar);
        });
    }

    @Test
    public void testUpdateTurnIndicator() {
        activityRule.getScenario().onActivity(activity -> {
            String currentPlayer = "White";

            activity.updateTurnIndicator(currentPlayer);
            TextView turnIndicator = activity.findViewById(R.id.turnIndicator);
            assertNotNull("Turn indicator TextView is not initialized", turnIndicator);
            String expectedText = currentPlayer + " Turn";
            assertEquals("Turn indicator text is not updated correctly", expectedText, turnIndicator.getText().toString());
        });
    }

    @Test
    public void testSetupGameBoard() {
        activityRule.getScenario().onActivity(activity -> {
            GridLayout chessBoard = activity.findViewById(R.id.chessBoard);
            int initialChildCount = chessBoard.getChildCount();
            activity.setupGameBoard();
            int finalChildCount = chessBoard.getChildCount();
            assertNotNull("Chess board should not be null", chessBoard);
            assertEquals("Chess board should have 64 squares after setup", 64, finalChildCount - initialChildCount);

            for (int i = 0; i < chessBoard.getChildCount(); i++) {
                View view = chessBoard.getChildAt(i);
                if (view instanceof ImageView) {
                    ImageView square = (ImageView) view;
                    String tag = (String) square.getTag();

                    if (tag != null) {
                        int drawableId = square.getDrawable() != null ? ((BitmapDrawable) square.getDrawable()).getBitmap().hashCode() : -1;
                        verifyPiecePlacement(tag, drawableId);
                    }
                }
            }
        });
    }

    private void verifyPiecePlacement(String tag, int drawableId) {

        String[] parts = tag.split(",");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        // Mapping each piece type and color to its corresponding drawable
        Map<String, Integer> pieceDrawableMap = new HashMap<>();
        pieceDrawableMap.put("PAWN_BLACK", R.drawable.pawn_black);
        pieceDrawableMap.put("PAWN_WHITE", R.drawable.pawn_white);
        pieceDrawableMap.put("ROOK_BLACK", R.drawable.rook_black);
        pieceDrawableMap.put("ROOK_WHITE", R.drawable.rook_white);
        pieceDrawableMap.put("KNIGHT_BLACK", R.drawable.knight_black);
        pieceDrawableMap.put("KNIGHT_WHITE", R.drawable.knight_white);
        pieceDrawableMap.put("BISHOP_WHITE", R.drawable.bishop_white);
        pieceDrawableMap.put("BISHOP_BLACK", R.drawable.bishop_black);
        pieceDrawableMap.put("KING_WHITE", R.drawable.king_white);
        pieceDrawableMap.put("KING_BLACK", R.drawable.king_black);
        pieceDrawableMap.put("QUEEN_WHITE", R.drawable.queen_white);
        pieceDrawableMap.put("QUEEN_BLACK", R.drawable.queen_black);

        String pieceKey = getPieceKeyForPosition(row, col);
        if (pieceKey != null && pieceDrawableMap.containsKey(pieceKey)) {
            assertEquals("Piece image mismatch at " + tag, pieceDrawableMap.get(pieceKey).hashCode(), drawableId);
        }
    }
    private String getPieceKeyForPosition(int row, int col) {
        if (row == 1) {
            return "P_BLACK";
        } else if (row == 6) {
            return "P_WHITE";
        } else if (row == 0 && (col == 0 || col == 7)) {
            return "R_BLACK";
        } else if (row == 7 && (col == 0 || col == 7)) {
            return "R_WHITE";
        } else if (row == 0 && (col == 1 || col == 6)) {
            return "K_BLACK";
        } else if (row == 7 && (col == 1 || col == 6)) {
            return "K_WHITE";
        } else if (row == 0 && (col == 2 || col == 5)) {
            return "B_BLACK";
        } else if (row == 7 && (col == 2 || col == 5)) {
            return "B_WHITE";
        } else if (row == 0 && (col == 3)) {
            return "KG_BLACK";
        } else if (row == 7 && (col == 3)) {
            return "KG_WHITE";
        } else if (row == 0 && (col == 4)) {
            return "QN_BLACK";
        } else if (row == 7 && (col == 4)) {
            return "QN_WHITE";
        }
        return null; // No piece expected at this position
    }

    @Test
    public void stopwatchUpdatesTextView() throws InterruptedException {
        // Specify the time in milliseconds you want to wait for the stopwatch to update
        final int waitTime = 2000; // 2 seconds, for example

        // Wait for the specified time
        Thread.sleep(waitTime);

        // Check if the TextView shows the expected time after waiting
        onView(withId(R.id.timerTextView)).check(matches(withText(containsString("00:03")))); //Added one second because of delay
    }

    @Test
    public void testMovePiece() {

        activityRule.getScenario().onActivity(activity -> {
            // Initialize your test squares, image views, and piece
            Square fromSquare = new Square(0, 1); // Example starting square
            Square toSquare = new Square(0, 2); // Example destination square
            Piece piece = new Pawn("WHITE", "P1W"); // Example piece
            fromSquare.setOccupiedBy(piece);

            // Mock or create ImageView objects
            ImageView fromView = new ImageView(activity);
            ImageView toView = new ImageView(activity);

            // Map for piece images
            Map<String, Integer> pieceImageMap = new HashMap<>();
            // Populate your pieceImageMap with test data

            // Call movePiece
            activity.movePiece(fromSquare, toSquare, fromView, toView, pieceImageMap, activity.getGameBoard(), activity);

            // Assert outcomes
            assertNull("Piece should be moved from original square", fromSquare.getOccupiedBy());
            assertEquals("Destination square should have the piece", piece, toSquare.getOccupiedBy());
            // Add more assertions as necessary
        });
    }

    /*
    @Test
    public void testCheckForCheckAndCheckmate() {
        // Test the checkForCheckAndCheckmate method
    }

    // Additional tests as needed

     */
}
