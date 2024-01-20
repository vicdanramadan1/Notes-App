package com.example.mynotes.di

import android.app.Application
import androidx.room.Room
import com.example.mynotes.feature_note.data.repository.NoteRepositoryImplementation
import com.example.mynotes.feature_note.data.source.NoteDatabase
import com.example.mynotes.feature_note.domain.repository.NoteRepository
import com.example.mynotes.feature_note.domain.use_case.AddNoteUseCase
import com.example.mynotes.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.mynotes.feature_note.domain.use_case.GetNoteUseCase
import com.example.mynotes.feature_note.domain.use_case.GetNotesUseCase
import com.example.mynotes.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(database: NoteDatabase) : NoteRepository
    {
        return NoteRepositoryImplementation(database.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase =  GetNoteUseCase(repository)
        )
    }
}