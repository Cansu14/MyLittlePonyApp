package com.example.mylittlepony.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mylittlepony.ui.screen.PonyDetailScreen
import com.example.mylittlepony.ui.screen.PonyListScreen
import com.example.mylittlepony.viewmodel.PonyDetailViewModel
import com.example.mylittlepony.viewmodel.PonyListViewModel

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.List.route
    ) {
        composable(Routes.List.route) {
            val listViewmodel: PonyListViewModel = hiltViewModel()
            PonyListScreen(
                viewmodel = listViewmodel,
                onPonyClick = { ponyId ->
                    navController.navigate(Routes.Detail.createRoute(ponyId))
                }
            )
        }

        composable(
            route = Routes.Detail.route,
            arguments = listOf(navArgument("ponyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val detailViewmodel: PonyDetailViewModel = hiltViewModel()
            val ponyId = backStackEntry.arguments?.getInt("ponyId") ?: 0
            PonyDetailScreen(
                viewmodel = detailViewmodel,
                ponyId = ponyId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}