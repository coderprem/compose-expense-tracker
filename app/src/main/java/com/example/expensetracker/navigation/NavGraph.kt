package com.example.expensetracker.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.ui.screens.AddExpenses
import com.example.expensetracker.ui.screens.HomeScreen
import com.example.expensetracker.ui.screens.ProfileScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "home") {
        composable(Screen.Home.route) {
            HomeScreen(modifier = modifier, navController = navController)
        }
        composable(Screen.Graph.route) {
            Text(text = "Graph", modifier = modifier)
        }
        composable(Screen.Wallet.route) {
            Text(text = "Wallet", modifier = modifier)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(modifier = modifier, navController = navController)
        }
        composable(Screen.AddExpenses.route) {
            AddExpenses(modifier = modifier, navController = navController)
        }
    }
}