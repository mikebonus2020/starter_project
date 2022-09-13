package com.example.noteapp.ui.detail

sealed class DetailEvent {

    data class OnTitleChange(val title: String): DetailEvent()
    data class OnDescriptionChange(val description: String): DetailEvent()
    data class OnDateChange(val date: String): DetailEvent()
    object OnSaveTodoClick: DetailEvent()

}
