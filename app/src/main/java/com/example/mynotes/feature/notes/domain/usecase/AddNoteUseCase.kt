package com.example.mynotes.feature.notes.domain.usecase

import com.example.mynotes.featurenote.domain.model.InvalidNoteException
import com.example.mynotes.featurenote.domain.model.Note
import com.example.mynotes.featurenote.domain.repository.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note): Result<Unit> {
        return when {
            note.title.isBlank() -> Result.failure(InvalidNoteException("title could not be empty"))
            note.content.isBlank() -> Result.failure(InvalidNoteException("content could not be empty"))
            else -> Result.success(repository.insertNote(note))
        }
    }
}