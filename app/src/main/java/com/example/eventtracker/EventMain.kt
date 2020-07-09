package com.example.eventtracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EventMain : AppCompatActivity(), EventTriggerDialogFragment.EventTriggerDialogListener {
    private val newEventActivityRequestCode = 1
    private lateinit var eventViewModel: EventViewModel
    private var fragmentManager: FragmentManager = supportFragmentManager

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        print("Dialog sent")
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        print("Dialog canceled")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = EventListAdapter(this, fragmentManager)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        eventViewModel.allEvents.observe(this, Observer { events ->
            events?.let { adapter.setEvents(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this@EventMain, CreateEventType::class.java)
            startActivityForResult(intent, newEventActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newEventActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(CreateEventType.EXTRA_REPLY)?.let {
                val event = Event(it)
                eventViewModel.insertEvent(event)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

}
