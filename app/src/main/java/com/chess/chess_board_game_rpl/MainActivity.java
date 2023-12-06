package com.chess.chess_board_game_rpl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button mDialogButton, dismissButton, playWithBot;
    private GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialogButton = findViewById(R.id.search_opponent);
        Dialog dialog = new Dialog(MainActivity.this);

        playWithBot = findViewById(R.id.play_with_bot);

        playWithBot.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Choose Difficulty");
            final String[] levels = {"EZ Mode", "Normal Mode", "Nightmare Mode"};
            // Use an array to hold the selected difficulty because it's effectively final
            final String[] selectedDifficulty = {levels[0]}; // default to the first option

            builder.setSingleChoiceItems(levels, 0, (dialog12, which) -> {
                selectedDifficulty[0] = levels[which];
                Toast.makeText(MainActivity.this, "Clicked on: " + levels[which], Toast.LENGTH_SHORT).show();
            });
            builder.setNegativeButton("Cancel", (dialog1, which) -> dialog1.dismiss());
            builder.setPositiveButton("Confirm", (dialog1, which) -> {
                // User clicked confirm, so check if the "Normal Mode" was selected
                if ("Normal Mode".equals(selectedDifficulty[0])) {
                    // Start the game activity
                    Intent intent = new Intent(MainActivity.this, InGameActivity.class);
                    intent.putExtra("difficulty", "Normal");
                    startActivity(intent);
                }
                dialog1.dismiss();
            });
            builder.show();
        });

        mDialogButton.setOnClickListener(v -> {
            dialog.setContentView(R.layout.custom_dialog_1);

            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.animation;

            dismissButton = dialog.findViewById(R.id.dismiss_button);
            dismissButton.setOnClickListener(v1 -> {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "okay clicked", Toast.LENGTH_SHORT).show();
            });

            dialog.show();
        });

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Chess Board");
        setSupportActionBar(myToolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId(); // Get the ID of the clicked menu item
        if (itemId == R.id.action_item1) {
            Intent intent = new Intent(this, Contributor.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_item2) {
            Intent intent = new Intent(this, InGameActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initializeUI() {
        // Set up the UI to reflect the initial state of the gameBoard
        GameBoard gameBoard = new GameBoard();

        // Assuming you have a method to create a view for each square
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                View squareView = createSquareView(gameBoard.getSquare(i, j));
                
                gridLayout.addView(squareView);

                // Set a tag to identify the square later
                squareView.setTag(new int[]{i, j});

                // Add click listeners to the squares
                squareView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // When a square is clicked, handle the move
                        int[] position = (int[]) v.getTag();
                        onSquareClicked(position[0], position[1]);
                    }
                });
            }
        }
    }

    private View createSquareView(Square square) {
        // Create and return a view for the square
        ImageView squareView = new ImageView(this);
        // You'll set the image or background depending on whether the square is occupied
        // and what piece is occupying it
        return squareView;
    }

    private void onSquareClicked(int x, int y) {
        // Handle the logic when a square is clicked
        // This could involve selecting a piece, moving a piece, etc.
    }
}
