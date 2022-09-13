package com.example.noteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// MODEL-CLASS
@Entity
data class Note(

    val title: String,
    val description: String?,
    val isDone: Boolean,
    val date: String?,
    @PrimaryKey val id: Int? = null

)
