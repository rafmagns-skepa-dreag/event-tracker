package com.example.eventtracker

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val eventButton = findViewById<Button>(R.id.event_button)
        eventButton.apply {
            text = message
        }
    }
}
