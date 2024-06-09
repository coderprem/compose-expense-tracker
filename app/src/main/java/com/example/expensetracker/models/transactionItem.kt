package com.example.expensetracker.models

import com.example.expensetracker.R

data class transactionItem (
    val imageId: Int = R.drawable.ic_netflix,
    val title: String = "Netflix",
    val date: String = "Today",
    val money: String = "5000"
)