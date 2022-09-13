package com.example.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.data.NoteDao
import com.example.noteapp.model.Note

// DATABASE
@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val notedao: NoteDao
}