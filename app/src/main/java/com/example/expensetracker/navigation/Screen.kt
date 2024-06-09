package com.example.expensetracker.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Graph : Screen("graph")
    data object Wallet : Screen("wallet")
    data object Profile : Screen("profile")
    data object AddExpenses : Screen("add_expenses")
}
