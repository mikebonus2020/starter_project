package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.data.NoteDatabase
import com.example.noteapp.data.NoteRepositoryImpl
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
    fun provideTodoDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.notedao)
    }
}