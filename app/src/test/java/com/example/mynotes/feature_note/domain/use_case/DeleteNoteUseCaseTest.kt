package com.example.mynotes.feature_note.domain.use_case

import com.example.mynotes.feature_note.data.repository.FakeNoteRepository
import com.example.mynotes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseTest{
    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var deleteNoteUseCase: DeleteNoteUseCase
    private lateinit var note: Note

    @Before
    fun setup()
    {
        fakeNoteRepository = FakeNoteRepository()
        deleteNoteUseCase = DeleteNoteUseCase(fakeNoteRepository)
        note = Note(title = "Title" , content = "Content" , color = 1 , timestamp = 1L , id = 1)
        runBlocking {
            fakeNoteRepository.insertNote(note)
        }
    }

    @Test
    fun `note deleted successfully`()
    {
        val notes: List<Note>
        runBlocking {
            fakeNoteRepository.deleteNote(note = note)
            notes = fakeNoteRepository.getNotes().flatMapConcat { it.asFlow() }.toList()
        }
        assertTrue(!notes.contains(note))
    }
}