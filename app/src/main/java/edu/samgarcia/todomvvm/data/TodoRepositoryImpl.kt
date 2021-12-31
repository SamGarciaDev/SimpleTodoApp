package edu.samgarcia.todomvvm.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao
): TodoRepository {
    override suspend fun insert(todo: Todo) {
        dao.insert(todo)
    }

    override suspend fun delete(todo: Todo) {
        dao.delete(todo)
    }

    override suspend fun getById(id: Int): Todo? {
        return dao.getById(id)
    }

    override fun getAll(): Flow<List<Todo>> {
        return dao.getAll()
    }

}