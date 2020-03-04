package com.example.eventtracker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class CreateEventType : AppCompatActivity() {
    private lateinit var editEventView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event_type)
        editEventView = findViewById(R.id.edit_event)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editEventView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val event = editEventView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, event)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.eventlistsql.REPLY"
    }
}
