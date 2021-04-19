package ru.zdanovich.handhSchoolHomework.data.mapper

import ru.zdanovich.handhSchoolHomework.data.entities.NoteEntity
import ru.zdanovich.handhSchoolHomework.domain.models.Note
import ru.zdanovich.handhSchoolHomework.domain.models.NoteColor

object NoteMapper {
    fun toNote(noteEntity: NoteEntity): Note =
        Note(
            id = noteEntity.id,
            title = noteEntity.title,
            body = noteEntity.body,
            isArchived = noteEntity.isArchived,
            noteColor = NoteColor(
                backgroundColor = noteEntity.backgroundColor,
                textColor = noteEntity.textColor
            )
        )

    fun toNoteEntity(note: Note): NoteEntity =
        NoteEntity(
            id = note.id,
            title = note.title,
            body = note.body,
            isArchived = note.isArchived,
            backgroundColor = note.noteColor.backgroundColor,
            textColor = note.noteColor.textColor
        )
}