package com.example.mynotes.feature.notes.domain.usecase

import com.example.mynotes.featurenote.domain.model.Note
import com.example.mynotes.featurenote.domain.repository.NoteRepository

class GetNoteUseCase(private  val repository: NoteRepository) {
    suspend operator fun invoke(id: Int): Note?{
        return  repository.getNoteById(id)
    }
}