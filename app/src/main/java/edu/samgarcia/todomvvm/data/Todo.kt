package edu.samgarcia.todomvvm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val description: String?,
    val isDone: Boolean
)
