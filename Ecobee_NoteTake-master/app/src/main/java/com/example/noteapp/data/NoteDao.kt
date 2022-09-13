package com.example.noteapp.data

import androidx.room.*
import com.example.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

// DAO-interface
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY title DESC LIMIT 1")
    suspend fun getLastInsertedNote(): List<Note>
}