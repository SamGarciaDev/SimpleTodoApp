package edu.samgarcia.todomvvm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.samgarcia.todomvvm.utils.Constants.TODO_TABLE_NAME

@Entity(tableName = TODO_TABLE_NAME)
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val description: String? = null,
    val isDone: Boolean
)
