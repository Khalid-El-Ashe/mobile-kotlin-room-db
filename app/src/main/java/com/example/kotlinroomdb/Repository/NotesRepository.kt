package com.example.kotlinroomdb.Repository

import androidx.lifecycle.LiveData
import com.example.kotlinroomdb.Dao.NotesDao
import com.example.kotlinroomdb.Model.Notes

class NotesRepository(val dao: NotesDao) {

    // in here i need to make my some functions to connect with viewModel class to use it

    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes) {
        return dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int) {
        return dao.deleteNote(id)
    }

    fun updateNotes(notes: Notes) {
        return dao.updateNotes(notes)
    }

    fun getHighNotes(): LiveData<List<Notes>> {
        return dao.getHighNotes()
    }

    fun getMediumNotes(): LiveData<List<Notes>> {
        return dao.getMediumNotes()
    }

    fun getLowNotes(): LiveData<List<Notes>> {
        return dao.getLowNotes()
    }
}