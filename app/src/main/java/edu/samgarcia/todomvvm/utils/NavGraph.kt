package edu.samgarcia.todomvvm.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import edu.samgarcia.todomvvm.ui.screens.add_edit_todo.AddEditTodoScreen
import edu.samgarcia.todomvvm.ui.screens.todo_list.TodoListScreen
import edu.samgarcia.todomvvm.utils.Constants.ADD_EDIT_TODO_ID_KEY

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.TODO_LIST
    ) {
        composable(Routes.TODO_LIST) {
            TodoListScreen(
                onNavigate = { event ->
                    navController.navigate(event.route)
                }
            )
        }

        composable(
            route = Routes.ADD_EDIT_TODO + "?$ADD_EDIT_TODO_ID_KEY={$ADD_EDIT_TODO_ID_KEY}",
            arguments = listOf(
                navArgument(name = ADD_EDIT_TODO_ID_KEY) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditTodoScreen(
                onPopBackStack = {
                    navController.popBackStack()
                }
            )
        }
    }
}