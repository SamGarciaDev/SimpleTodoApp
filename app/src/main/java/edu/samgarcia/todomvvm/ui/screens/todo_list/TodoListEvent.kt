package edu.samgarcia.todomvvm.ui.screens.todo_list

import edu.samgarcia.todomvvm.data.Todo

sealed class TodoListEvent {
    object OnAddTodoClick: TodoListEvent()
    data class OnTodoClick(val todo: Todo): TodoListEvent()
    data class OnDeleteClick(val todo: Todo): TodoListEvent()
    object OnUndoDeleteClick: TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean): TodoListEvent()
}
