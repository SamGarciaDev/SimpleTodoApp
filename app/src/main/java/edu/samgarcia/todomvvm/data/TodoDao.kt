package edu.samgarcia.todomvvm.data

import androidx.room.*
import edu.samgarcia.todomvvm.utils.Constants.TODO_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM $TODO_TABLE_NAME WHERE id=:id")
    suspend fun getById(id: Int): Todo?

    @Query("SELECT * FROM $TODO_TABLE_NAME")
    fun getAll(): Flow<List<Todo>>
}