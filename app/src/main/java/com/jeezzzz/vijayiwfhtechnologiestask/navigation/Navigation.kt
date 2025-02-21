package com.jeezzzz.vijayiwfhtechnologiestask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jeezzzz.vijayiwfhtechnologiestask.ui.screens.DetailsScreen
import com.jeezzzz.vijayiwfhtechnologiestask.ui.screens.HomeScreen
import com.jeezzzz.vijayiwfhtechnologiestask.viewModels.WatchModeViewModel

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Details : Screen("details/{id}") {
        fun createRoute(id: Int) = "details/$id"
    }
}

@Composable
fun Navigation(viewModel: WatchModeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController, viewModel)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetailsScreen(navController, viewModel, id)
        }
    }
}
