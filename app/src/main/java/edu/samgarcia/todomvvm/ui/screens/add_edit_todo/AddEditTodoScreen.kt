package edu.samgarcia.todomvvm.ui.screens.add_edit_todo

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.samgarcia.todomvvm.utils.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar() {
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Add to-do item 🖊",
                        fontSize = 20.sp
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditTodoEvent.OnSaveTodoClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.title,
                onValueChange = { value ->
                    viewModel.onEvent(AddEditTodoEvent.OnTitleChange(value))
                },
                placeholder = {
                    Text(text = "To-do title")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(AddEditTodoEvent.OnTitleChange(""))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                },
                label = {
                    Text(text = "Title")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.description,
                onValueChange = { value ->
                    viewModel.onEvent(AddEditTodoEvent.OnDescriptionChange(value))
                },
                placeholder = {
                    Text(text = "Write the details here...")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5,
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(AddEditTodoEvent.OnDescriptionChange(""))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                },
                label = {
                    Text(text = "Description")
                }
            )
        }
    }
}