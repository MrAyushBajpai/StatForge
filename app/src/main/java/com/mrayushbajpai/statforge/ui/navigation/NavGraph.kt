package com.mrayushbajpai.statforge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrayushbajpai.statforge.ui.screens.HomeScreen
import com.mrayushbajpai.statforge.ui.screens.QuestScreen
import com.mrayushbajpai.statforge.ui.screens.StatsScreen
import com.mrayushbajpai.statforge.ui.viewmodel.StatViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Quests : Screen("quests")
    object Stats : Screen("stats")
}

@Composable
fun StatForgeNavGraph(viewModel: StatViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToQuests = { navController.navigate(Screen.Quests.route) },
                onNavigateToStats = { navController.navigate(Screen.Stats.route) }
            )
        }
        composable(Screen.Quests.route) {
            QuestScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Stats.route) {
            StatsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
