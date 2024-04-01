package com.example.mynotes.featurenote.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.mynotes.feature.notes.data.repository.NoteRepositoryImpl
import com.example.mynotes.featurenote.data.source.NoteDatabase
import com.example.mynotes.featurenote.domain.repository.NoteRepository
import com.example.mynotes.feature.notes.domain.usecase.AddNoteUseCase
import com.example.mynotes.feature.notes.domain.usecase.DeleteNoteUseCase
import com.example.mynotes.feature.notes.domain.usecase.GetNoteUseCase
import com.example.mynotes.featurenote.domain.usecase.GetNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(app, NoteDatabase::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(database: NoteDatabase) : NoteRepository
    {
        return NoteRepositoryImpl(database.noteDao)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(repository: NoteRepository): DeleteNoteUseCase = DeleteNoteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetNotesUseCase (repository: NoteRepository) : GetNotesUseCase = GetNotesUseCase(repository)

    @Provides
    @Singleton
    fun provideAddNoteUseCase(repository: NoteRepository) : AddNoteUseCase = AddNoteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetNoteUseCase(repository: NoteRepository): GetNoteUseCase = GetNoteUseCase(repository)
}