package com.example.personalrun

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PersonalRun() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "activity_main") { // Altere para "activity_main"
        composable(route = "activity_main") {
            MainScreen(navController) // Tela inicial configurada como MainScreen
        }
        composable(route = "activity_category") {
            ActivityCategoryScreen(navController)
        }
        composable(route = "activityList/{type}") { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            ActivityDadosScreen(type = type) // Passe o tipo para a tela de dados
        }
        composable(route = "activity_statistics") {
            ProfileWithStatistics(navController)
        }
    }
}

