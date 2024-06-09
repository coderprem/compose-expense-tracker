package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.navigation.NavGraph
import com.example.expensetracker.navigation.Screen
import com.example.expensetracker.ui.screens.botNavItems
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTrackerTheme {
                var selected by remember {
                    mutableIntStateOf(0)
                }
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            botNavItems.forEachIndexed { index, bottomNavItem ->
                                NavigationBarItem(selected = index == selected, onClick = {
                                    selected = index
                                    navController.navigate(bottomNavItem.route) {
                                        popUpTo(navController.graph.startDestinationId)
                                    }
                                }, icon = {
                                    BadgedBox(badge = {
                                        if (bottomNavItem.badgeCount != 0) {
                                            Badge {
                                                Text(text = bottomNavItem.badgeCount.toString())
                                            }
                                        } else {
//                                                Badge()
                                        }
                                    }) {
                                        Icon(
                                            painter = painterResource(
                                                id = if (index == selected) bottomNavItem.selectedIcon
                                                else bottomNavItem.unselectedIcon
                                            ),
                                            contentDescription = bottomNavItem.title,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }, label = {
                                    bottomNavItem.title
                                })
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            navController.navigate(Screen.AddExpenses.route)
                        }) {
                            Icon(Icons.Filled.Add, contentDescription = "Add")
                        }
                    }
                ) { innerPadding ->
                    NavGraph(modifier = Modifier.padding(innerPadding), navController = navController)
                }
            }
        }
    }
}

