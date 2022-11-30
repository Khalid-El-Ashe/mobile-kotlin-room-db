package com.example.kotlinroomdb.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinroomdb.Database.NotesDatabase
import com.example.kotlinroomdb.Model.Notes
import com.example.kotlinroomdb.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    // this class to connect with view ( activities or fragments ) to use this functions

    val repository: NotesRepository

    // this block to make insiolize for object
    init {
        // i need to make object of dao val
        val dao = NotesDatabase.getDatabaseInstance(application).myNoteDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()
    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }

    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()
    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()
}