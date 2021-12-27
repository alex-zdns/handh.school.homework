package ru.zdanovich.handhSchoolHomework.presenter.notesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.zdanovich.handhSchoolHomework.NoteApp
import ru.zdanovich.handhSchoolHomework.data.repositories.NoteRepositoryImpl

class NotesListViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        NotesListViewModel::class.java -> NotesListViewModel(NoteRepositoryImpl(NoteApp.context()))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}