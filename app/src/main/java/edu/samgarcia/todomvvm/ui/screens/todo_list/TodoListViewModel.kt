package edu.samgarcia.todomvvm.ui.screens.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.samgarcia.todomvvm.data.Todo
import edu.samgarcia.todomvvm.data.TodoRepository
import edu.samgarcia.todomvvm.utils.Constants.ADD_EDIT_TODO_ID_KEY
import edu.samgarcia.todomvvm.utils.Routes
import edu.samgarcia.todomvvm.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {
    val todos = repository.getAllTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(
                    event = UiEvent.Navigate(Routes.ADD_EDIT_TODO)
                )
            }
            is TodoListEvent.OnTodoClick -> {
                sendUiEvent(
                    event = UiEvent.Navigate(
                        route = Routes.ADD_EDIT_TODO + "?$ADD_EDIT_TODO_ID_KEY=${event.todo.id}"
                    )
                )
            }
            is TodoListEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUiEvent(
                        event = UiEvent.ShowSnackbar(
                            message = "Todo deleted",
                            action = "Undo"
                        )
                    )
                }
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertTodo(event.todo.copy(isDone = event.isDone))
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