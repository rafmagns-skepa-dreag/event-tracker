package com.example.eventtracker

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {
    @Query("SELECT * FROM event ORDER BY event.event ASC")
    fun getAlphabetizedEvents(): LiveData<List<Event>>

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Query("DELETE FROM event")
    suspend fun deleteAll()
}