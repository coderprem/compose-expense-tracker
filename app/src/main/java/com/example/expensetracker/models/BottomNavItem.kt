package com.example.expensetracker.models

data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int = selectedIcon,
    val hasNews: Boolean = false,
    val badgeCount: Int = 0
)