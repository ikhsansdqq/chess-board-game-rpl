package com.chess.chess_board_game_rpl;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button mDialogButton, dismissButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialogButton = findViewById(R.id.search_opponent);
        Dialog dialog = new Dialog(MainActivity.this);

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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
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
            Toast.makeText(this, "Action Item 2", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}