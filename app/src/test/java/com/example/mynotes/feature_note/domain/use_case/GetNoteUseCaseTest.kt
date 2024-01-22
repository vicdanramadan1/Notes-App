package com.example.mynotes.feature_note.domain.use_case

import com.example.mynotes.feature_note.data.repository.FakeNoteRepository
import com.example.mynotes.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetNoteUseCaseTest{
    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var getNoteUseCase: GetNoteUseCase
    private lateinit var note: Note

    @Before
    fun setup()
    {
        fakeNoteRepository = FakeNoteRepository()
        getNoteUseCase = GetNoteUseCase(fakeNoteRepository)
        note = Note(title = "Title" , content = "Content" , color = 1 , timestamp = 1L , id = 1)
        runBlocking {
            fakeNoteRepository.insertNote(note)
        }
    }

    @Test
    fun `get note by id successfully`()
    {
        val retrievedNote : Note
        runBlocking {
            retrievedNote = fakeNoteRepository.getNoteById(note.id!!)!!
        }
        assertEquals(retrievedNote , note)
    }
}