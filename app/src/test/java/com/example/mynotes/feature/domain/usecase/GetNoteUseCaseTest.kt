package com.example.mynotes.featurenote.domain.usecase

import com.example.mynotes.feature.notes.domain.usecase.GetNoteUseCase
import com.example.mynotes.featurenote.data.repository.FakeNoteRepository
import com.example.mynotes.featurenote.domain.model.Note
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