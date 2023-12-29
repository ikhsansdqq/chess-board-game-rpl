package com.chess.chess_board_game_rpl.history;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ChessDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "chessGames"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    // Table and columns
    private static final String TABLE_NAME = "ChessGameHistory";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PLAYER1 = "player1";
    private static final String COLUMN_PLAYER2 = "player2";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME_PLAYING = "time_playing";
    private static final String COLUMN_MOVES_PLAYER1 = "moves_player1";
    private static final String COLUMN_MOVES_PLAYER2 = "moves_player2";
    private static final String COLUMN_KILLED_PIECES_PLAYER1 = "killed_pieces_player1";
    private static final String COLUMN_KILLED_PIECES_PLAYER2 = "killed_pieces_player2";
    private static final String COLUMN_WINNER = "winner";

    public ChessDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PLAYER1 + " TEXT, "
                + COLUMN_PLAYER2 + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_TIME_PLAYING + " TEXT, "
                + COLUMN_MOVES_PLAYER1 + " TEXT, "
                + COLUMN_MOVES_PLAYER2 + " TEXT, "
                + COLUMN_KILLED_PIECES_PLAYER1 + " TEXT, "
                + COLUMN_KILLED_PIECES_PLAYER2 + " TEXT, "
                + COLUMN_WINNER + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    public void addGame(String player1, String player2, String date, String timePlaying,
                        String movesPlayer1, String movesPlayer2,
                        String killedPiecesPlayer1, String killedPiecesPlayer2,
                        String winner) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYER1, player1);
        values.put(COLUMN_PLAYER2, player2);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME_PLAYING, timePlaying);
        values.put(COLUMN_MOVES_PLAYER1, movesPlayer1);
        values.put(COLUMN_MOVES_PLAYER2, movesPlayer2);
        values.put(COLUMN_KILLED_PIECES_PLAYER1, killedPiecesPlayer1);
        values.put(COLUMN_KILLED_PIECES_PLAYER2, killedPiecesPlayer2);
        values.put(COLUMN_WINNER, winner);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<String> getAllGames() {
        List<String> games = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String game = "ID: " + cursor.getInt(0) + ", Player1: " + cursor.getString(1)
                        + ", Player2: " + cursor.getString(2) + ", Date: " + cursor.getString(3)
                        + ", Time Playing: " + cursor.getString(4) + ", Moves Player1: " + cursor.getString(5)
                        + ", Moves Player2: " + cursor.getString(6) + ", Killed Pieces Player1: " + cursor.getString(7)
                        + ", Killed Pieces Player2: " + cursor.getString(8) + ", Winner: " + cursor.getString(9);
                games.add(game);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return games;
    }
}
