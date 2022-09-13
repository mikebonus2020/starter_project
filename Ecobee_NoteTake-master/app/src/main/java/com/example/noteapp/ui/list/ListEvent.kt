package com.example.noteapp.ui.list

import com.example.noteapp.model.Note

sealed class ListEvent {
    data class OnDeleteTodoClick(val note: Note): ListEvent()
    data class OnDoneChange(val note: Note, val isDone: Boolean): ListEvent()
    object OnUndoDeleteClick: ListEvent()
    data class OnTodoClick(val note: Note): ListEvent()
    object OnAddTodoClick: ListEvent()
}
