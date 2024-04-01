package com.example.mynotes.feature.notes.domain.usecase

import com.example.mynotes.featurenote.domain.model.Note
import com.example.mynotes.featurenote.domain.repository.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke (note: Note)
    {
        repository.deleteNote(note)
    }
}