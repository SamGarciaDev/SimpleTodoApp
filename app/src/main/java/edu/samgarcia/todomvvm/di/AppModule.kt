package edu.samgarcia.todomvvm.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.samgarcia.todomvvm.data.TodoDatabase
import edu.samgarcia.todomvvm.data.TodoRepository
import edu.samgarcia.todomvvm.data.TodoRepositoryImpl
import edu.samgarcia.todomvvm.utils.Constants.TODO_DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            TODO_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.dao)
    }
}