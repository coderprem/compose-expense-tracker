package com.example.expensetracker.data

import java.time.Instant
import java.util.Date

data class RegistrationUIState(
    var typeOfExpense: String = "",
    var name: String = "",
    var category: String = "",
    var amount: Double = 0.0,
    var date: Date = Date.from(Instant.now()),
    var incomeAmount: Double = 0.0,
    var expenseAmount: Double = 0.0,
    var totalAmount: Double = 0.0,

    var typeOfExpenseError: Boolean = false,
    var nameError: Boolean = false,
    var categoryError: Boolean = false,
    var amountError: Boolean = false,
    var dateError: Boolean = false
)