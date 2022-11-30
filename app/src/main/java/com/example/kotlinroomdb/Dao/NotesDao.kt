package com.example.kotlinroomdb.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.kotlinroomdb.Model.Notes

@Dao
interface NotesDao {

    @Query("select * from Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("select * from Notes where priority=3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("select * from Notes where priority=2")
    fun getMediumNotes(): LiveData<List<Notes>>

    @Query("select * from Notes where priority=1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("delete from Notes where id= :id")
    fun deleteNote(id: Int)

    @Update
    fun updateNotes(notes: Notes)
}