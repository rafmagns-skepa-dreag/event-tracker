package com.example.eventtracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
class Event(
    @PrimaryKey @ColumnInfo(name="event") val event: String
)