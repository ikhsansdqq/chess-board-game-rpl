package com.chess.chess_board_game_rpl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

data class PlayerData(
    val avatar: String,
    val playerId: Long,
    val id: String,
    val url: String,
    val name: String,
    val username: String,
    val followers: Int,
    val country: String,
    val lastOnline: Long,
    val joined: Long,
    val status: String,
    val isStreamer: Boolean,
    val verified: Boolean
)

class ApiTestScreen : AppCompatActivity() {
    var name = "ikhsansdq"
    var base_url = "https://api.chess.com/pub/player/$name"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_test_screen)


    }

    fun parseJson(jsonString: String): PlayerData {
        val json = JSONObject(jsonString)
        return PlayerData(
            json.getString("avatar"),
            json.getLong("player_id"),
            json.getString("@id"),
            json.getString("url"),
            json.getString("name"),
            json.getString("username"),
            json.getInt("followers"),
            json.getString("country"),
            json.getLong("last_online"),
            json.getLong("joined"),
            json.getString("status"),
            json.getBoolean("is_streamer"),
            json.getBoolean("verified")
        )
    }
}