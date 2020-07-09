package com.example.eventtracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class EventListAdapter internal constructor(
    context: Context, fragmentManager: FragmentManager
) : RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var events = emptyList<Event>() // Cached copy
    private var manager = fragmentManager

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventItemView: Button = itemView.findViewById(R.id.event_button)

        fun showEventTriggerDialog() {
            val dialog = EventTriggerDialogFragment()
            dialog.show(manager, "EventTriggerDialogFragment")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = events[position]
        holder.eventItemView.text = current.event
        holder.eventItemView.setOnClickListener {
            holder.showEventTriggerDialog()
        }
    }

    internal fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    override fun getItemCount() = events.size
}
