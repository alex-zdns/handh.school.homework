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
) : ViewModel() {
    private val _mutableNotesList = MutableLiveData<State>(State.Init)
    val notesList: LiveData<State> get() = _mutableNotesList

    init {
        viewModelScope.launch {
            repository.getAllNotes()
                .collect { list ->
                    _mutableNotesList.value = if (list.isEmpty()) {
                        State.EmptyList
                    } else {
                        State.Success(list)
                    }
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

    fun archieNote(noteId: Int) {
        viewModelScope.launch {
            repository.archiveNote(noteId)
        }
    }

    sealed class State {
        object Init : State()
        object EmptyList : State()
        class Success(val notes: List<Note>) : State()
    }
}