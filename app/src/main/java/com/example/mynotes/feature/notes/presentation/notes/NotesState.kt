package com.example.mynotes.featurenote.presentation.notes

import com.example.mynotes.featurenote.domain.model.Note
import com.example.mynotes.feature.notes.domain.util.NoteOrder
import com.example.mynotes.featurenote.domain.util.OrderType

data class NotesState(
    val notesList: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSelectionVisible: Boolean = false
)