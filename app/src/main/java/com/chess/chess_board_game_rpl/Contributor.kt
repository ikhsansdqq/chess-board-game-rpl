package com.chess.chess_board_game_rpl

import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class Contributor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor)

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.title = "Our Great Team"
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val contributor = arrayOf(
            "Fauzan Rizqi Muhammad",
            "Ikhsan Assidiqie",
            "Dawwi Raissa Damarjati Muljana",
            "Dinanti Aldilla",
            "M. Ridho Sunation"
        )
        val listView = findViewById<ListView>(R.id.contributor_listview)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contributor)
        listView.adapter = adapter

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val role = when (contributor[position]) {
                    "Fauzan Rizqi Muhammad" -> "Mobile Developer"
                    "Ikhsan Assidiqie" -> "Mobile Developer"
                    "Dawwi Raissa Damarjati Muljana" -> "UI/ UX Designer"
                    "Dinanti Aldilla" -> "UX Researcher"
                    "M. Ridho Sunation" -> "Legal Documentation"
                    else -> {
                        "Invalid data"
                    }
                }
                Toast.makeText(applicationContext, role, Toast.LENGTH_SHORT).show()
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}