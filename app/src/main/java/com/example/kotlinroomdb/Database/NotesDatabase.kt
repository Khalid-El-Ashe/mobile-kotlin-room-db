package com.example.kotlinroomdb.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinroomdb.Dao.NotesDao
import com.example.kotlinroomdb.Model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    // i need to get my DAO
    abstract fun myNoteDao(): NotesDao

    // i need to make static value in kotlin with companion object
    companion object {

        @Volatile
        var INSTANCE: NotesDatabase? = null

        fun getDatabaseInstance(context: Context): NotesDatabase {
            // i need to make instance from database
            val tempInstance = INSTANCE

            // i need to check if i have instance of database or note
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                // this is to make my roomDatabase
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "Notes")
                        .allowMainThreadQueries().build()

                INSTANCE = roomDatabaseInstance

                return return roomDatabaseInstance
            }
        }
    }
}