package edu.samgarcia.todomvvm.ui.screens.todo_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.samgarcia.todomvvm.data.Todo
import edu.samgarcia.todomvvm.ui.theme.Grey100

@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Grey100)
            .padding(top = 8.dp, end = 16.dp, bottom = 16.dp, start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.title,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        onEvent(TodoListEvent.OnDeleteClick(todo))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }
            }

            todo.description?.let { desc ->
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = desc,
                    Modifier.alpha(0.8f)
                )
            }
        }

        Checkbox(
            checked = todo.isDone,
            onCheckedChange = { isChecked ->
                onEvent(TodoListEvent.OnDoneChange(todo, isChecked))
            }
        )
    }
}

@Preview
@Composable
fun TodoItemPrev() {
    TodoItem(
        todo = Todo(
            id = 14,
            title = "Go to gym",
            description = "Don't be lazy, stupid!",
            isDone = false
        ),
        onEvent = { }
    )
}