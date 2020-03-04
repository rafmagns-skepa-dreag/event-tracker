package com.example.eventtracker

import androidx.lifecycle.LiveData

// Pass the DAO instead of the whole db cause that's what we need
class EventRepository(private val eventDao: EventDao) {
    val allEvents: LiveData<List<Event>> = eventDao.getAlphabetizedEvents()

    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }
}