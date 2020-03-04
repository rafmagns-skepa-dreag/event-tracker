package com.example.eventtracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EventRepository
    val allEvents: LiveData<List<Event>>

    init {
        val eventDao = EventRoomDatabase.getDatabase(application, viewModelScope).eventDao()
        repository = EventRepository(eventDao)
        allEvents = repository.allEvents
    }

    fun insertEvent(event: Event) = viewModelScope.launch {
        repository.insert(event)
    }
}