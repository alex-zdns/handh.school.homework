package ru.zdanovich.handhSchoolHomework.presenter.notesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.zdanovich.handhSchoolHomework.domain.models.Note
import ru.zdanovich.handhSchoolHomework.domain.repositories.NoteRepository

class NotesListViewModel(
    private val repository: NoteRepository
): ViewModel() {

    fun saveNote(note: Note) {
        viewModelScope.launch {
            repository.saveNote(note)
        }
    }
}