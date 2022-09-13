package com.example.noteapp.repository

import com.example.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

// ABSTRACT
interface NoteRepository {

    suspend fun insertTodo(note: Note)

    suspend fun deleteTodo(note: Note)

    suspend fun getTodoById(id: Int): Note?

    fun getTodos(): Flow<List<Note>>

}