package com.example.mynotes.feature_note.domain.use_case

import com.example.mynotes.feature_note.data.repository.FakeNoteRepository
import com.example.mynotes.feature_note.domain.model.InvalidNoteException
import com.example.mynotes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest{
    private lateinit var addNotesUseCase: AddNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup()
    {
        fakeNoteRepository = FakeNoteRepository()
        addNotesUseCase = AddNoteUseCase(fakeNoteRepository)
    }

    @Test
     fun `throw invalid exception , title could not be empty` () {
       val exception =  assertThrows(
            InvalidNoteException::class.java,
        ) {
            runBlocking {
                addNotesUseCase.invoke(
                    Note(
                        title = "",
                        color = 1,
                        timestamp = 1L,
                        id = 1,
                        content = "content"
                    )
                )
            }
        }
        assertEquals("title could not be empty" , exception.message)
    }

    @Test
    fun `throw invalid exception , content could not be empty` () {
        val exception =  assertThrows(
            InvalidNoteException::class.java,
        ) {
            runBlocking {
                addNotesUseCase.invoke(
                    Note(
                        title = "title",
                        color = 1,
                        timestamp = 1L,
                        id = 1,
                        content = ""
                    )
                )
            }
        }
        assertEquals("content could not be empty" , exception.message)
    }

    @Test
    fun `note successfully inserted` () {
        val note = Note(title = "Title" , content = "Content" , timestamp = 1L , color = 1 , id = 1)
        val notes: List<Note>
        runBlocking {
            fakeNoteRepository.insertNote(note)
        }
        runBlocking {
            notes = fakeNoteRepository.getNotes().flatMapConcat { it.asFlow() }.toList()
        }
        assertTrue(notes.contains(note))
    }
}