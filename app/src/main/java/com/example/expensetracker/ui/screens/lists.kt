package com.example.expensetracker.ui.screens

import com.example.expensetracker.R
import com.example.expensetracker.models.BottomNavItem
import com.example.expensetracker.navigation.Screen
import com.example.expensetracker.models.transactionItem

val botNavItems = listOf(
    BottomNavItem("Home", Screen.Home.route, R.drawable.filled_home_24, R.drawable.outline_home_24),
    BottomNavItem("Graph", Screen.Graph.route, R.drawable.graph_filled, R.drawable.graph_outlined),
    BottomNavItem("Wallet", Screen.Wallet.route, R.drawable.wallet_filled, R.drawable.wallet_outline),
    BottomNavItem("Profile", Screen.Profile.route, R.drawable.filled_person_24, R.drawable.outline_person_24)
)

val dropDownList = listOf(
    dropDownItem(
        imageId = R.drawable.ic_netflix,
        title = "Netflix"
    ),
    dropDownItem(
        imageId = R.drawable.ic_starbucks,
        title = "Starbucks"
    ),
    dropDownItem(
        imageId = R.drawable.ic_paypal,
        title = "Paypal"
    ),
    dropDownItem(
        imageId = R.drawable.ic_youtube,
        title = "Youtube"
    ),
    dropDownItem(
        imageId = R.drawable.ic_upwork,
        title = "Upwork"
    ),
)

data class dropDownItem(
    val imageId: Int,
    val title: String
)

val profileItemList = listOf(
    dropDownItem(
        imageId = R.drawable.filled_person_24,
        title = "Account Info"
    ),
    dropDownItem(
        imageId = R.drawable.baseline_people_alt_24,
        title = "Personal Profile"
    ),
    dropDownItem(
        imageId = R.drawable.baseline_email_24,
        title = "Message Center"
    ),
    dropDownItem(
        imageId = R.drawable.baseline_security_24,
        title = "Login & Security"
    ),
    dropDownItem(
        imageId = R.drawable.baseline_lock_24,
        title = "Data & Privacy"
    ),
)