package ru.zdanovich.handhSchoolHomework.presenter.notesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.zdanovich.handhSchoolHomework.domain.models.Note
import ru.zdanovich.handhSchoolHomework.domain.repositories.NoteRepository

class NotesListViewModel(
    private val repository: NoteRepository
): ViewModel() {
    private val _mutableNotesList = MutableLiveData<List<Note>>(emptyList())
    val notesList: LiveData<List<Note>> get() = _mutableNotesList

    init {
        viewModelScope.launch {
            repository.getAllNotes()
                .collect { list ->
                    _mutableNotesList.value = list
                }
        }
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            repository.saveNote(note)
        }
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }
}