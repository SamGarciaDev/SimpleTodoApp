package edu.samgarcia.todomvvm.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun insert(todo: Todo)

    suspend fun delete(todo: Todo)

    suspend fun getById(id: Int): Todo?

    fun getAll(): Flow<List<Todo>>
}