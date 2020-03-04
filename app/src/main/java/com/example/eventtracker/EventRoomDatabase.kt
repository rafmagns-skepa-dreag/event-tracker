package com.example.eventtracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Event::class), version = 1, exportSchema = false)
public abstract class EventRoomDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        // Use singleton database connection
        @Volatile
        private var INSTANCE: EventRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): EventRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventRoomDatabase::class.java,
                    "event"
                )
//                    .addCallback(EventDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

//    private class EventDatabaseCallback(
//        private val scope: CoroutineScope
//    ): RoomDatabase.Callback() {
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    populateDatabase(database.eventDao())
//                }
//            }
//        }

//        suspend fun populateDatabase(eventDao: EventDao) {
//            eventDao.deleteAll()
//            eventDao.insert(Event("Welbutrin"))
//            eventDao.insert(Event("fish"))
//            eventDao.insert(Event("late lunch"))
//        }
//    }
}