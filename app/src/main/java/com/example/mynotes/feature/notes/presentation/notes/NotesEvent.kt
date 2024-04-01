package com.example.mynotes.featurenote.presentation.notes

import com.example.mynotes.featurenote.domain.model.Note
import com.example.mynotes.feature.notes.domain.util.NoteOrder

sealed class NotesEvent{
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class Delete(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
