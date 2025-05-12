package com.example.personalrun

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personalrun.statistics.presentation.ui.ProfileWithStatistics
import com.example.personalrun.activity.presentation.ActivityViewModel
import com.example.personalrun.activity.presentation.ui.ActivityCategoryScreen
import com.example.personalrun.activity.presentation.ui.ActivityDadosScreen
import com.example.personalrun.statistics.presentation.StatisticsViewModel
import com.example.personalrun.statistics.presentation.ui.MainScreen

@Composable
fun PersonalRun(stravaApiService: ActivityViewModel.Companion, statisticsService: StatisticsViewModel.Companion) {


    val navController = rememberNavController()
    val activityViewModel: ActivityViewModel = viewModel(factory = ActivityViewModel.Factory)
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
            val statisticsViewModel: StatisticsViewModel = viewModel(factory = StatisticsViewModel.Factory)
            ProfileWithStatistics(navController, statisticsViewModel)
        }
    }
}

