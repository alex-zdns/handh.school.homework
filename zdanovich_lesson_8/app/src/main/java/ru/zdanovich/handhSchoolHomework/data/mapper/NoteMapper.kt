package ru.zdanovich.handhSchoolHomework.data.mapper

import ru.zdanovich.handhSchoolHomework.data.entities.NoteEntity
import ru.zdanovich.handhSchoolHomework.domain.models.Note

object NoteMapper {
    fun toNote(noteEntity: NoteEntity): Note =
        Note(
            id = noteEntity.id,
            title = noteEntity.title,
            body = noteEntity.body
        )

    fun toNoteEntity(note: Note): NoteEntity =
        NoteEntity(
            id = note.id,
            title = note.title,
            body = note.body
        )
}