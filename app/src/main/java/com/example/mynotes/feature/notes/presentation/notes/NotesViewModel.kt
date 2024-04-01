package com.example.mynotes.featurenote.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.feature.notes.domain.usecase.AddNoteUseCase
import com.example.mynotes.feature.notes.domain.usecase.DeleteNoteUseCase
import com.example.mynotes.feature.notes.domain.util.NoteOrder
import com.example.mynotes.featurenote.domain.model.Note
import com.example.mynotes.featurenote.domain.usecase.GetNotesUseCase
import com.example.mynotes.featurenote.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val addNote: AddNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val getNotes: GetNotesUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getAllNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (shouldOrderNotes(event.noteOrder)) {
                    getAllNotes(event.noteOrder)
                }
            }

            is NotesEvent.Delete -> {
                viewModelScope.launch { deleteNote(event.note) }
                recentlyDeletedNote = event.note
            }

            is NotesEvent.RestoreNote -> {
                recentlyDeletedNote?.let { note ->
                    viewModelScope.launch {
                        addNote(note)
                        recentlyDeletedNote = null
                    }
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value =
                    state.value.copy(isOrderSelectionVisible = !state.value.isOrderSelectionVisible)
            }

            else -> {}
        }
    }

    private fun getAllNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = getNotes(noteOrder).onEach { notes ->
            _state.value = state.value.copy(notesList = notes, noteOrder = noteOrder)
        }
            .launchIn(viewModelScope)
    }

    private fun shouldOrderNotes(noteOrder: NoteOrder): Boolean
    {
        return !(state.value.noteOrder::class == noteOrder::class
                && state.value.noteOrder.orderType == noteOrder.orderType)
    }
}