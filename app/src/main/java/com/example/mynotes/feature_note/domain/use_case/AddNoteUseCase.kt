package com.example.mynotes.feature_note.domain.use_case

import com.example.mynotes.feature_note.domain.model.InvalidNoteException
import com.example.mynotes.feature_note.domain.model.Note
import com.example.mynotes.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note)
    {
        if(note.title.isBlank()) {
            throw InvalidNoteException("title could not be empty")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("content could not be empty")
        }
        repository.insertNote(note)
    }
}