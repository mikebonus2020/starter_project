package com.example.noteapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.model.Note
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.util.Routes
import com.example.noteapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    val todos = repository.getTodos()

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedNote: Note? = null

    fun onEvent(event: ListEvent) {
        when(event) {
            is ListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.note.id}"))
            }
            is ListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is ListEvent.OnUndoDeleteClick -> {
                deletedNote?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is ListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedNote = event.note
                    repository.deleteTodo(event.note)
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }
            }
            is ListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertTodo(
                        event.note.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}