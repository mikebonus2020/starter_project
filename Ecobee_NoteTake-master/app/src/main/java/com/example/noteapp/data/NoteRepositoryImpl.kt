package com.example.noteapp.data

import com.example.noteapp.model.Note
import com.example.noteapp.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

// CONCRETE
class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {

    override suspend fun insertTodo(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteTodo(note: Note) {
        dao.deleteNote(note)
    }

    override suspend fun getTodoById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override fun getTodos(): Flow<List<Note>> {
        return dao.getNotes()
    }
}