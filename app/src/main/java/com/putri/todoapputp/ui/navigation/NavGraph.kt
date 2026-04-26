package com.putri.todoapputp.ui.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.putri.todoapputp.ui.screen.list.ListScreen
import com.putri.todoapputp.ui.screen.add.AddScreen
import com.putri.todoapputp.ui.screen.detail.DetailScreen

@Composable
fun NavGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            ListScreen(navController)
        }

        composable("add") {
            AddScreen(navController)
        }

        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments
                ?.getString("taskId")
                ?.toIntOrNull()

            DetailScreen(navController, taskId)
        }
    }
}