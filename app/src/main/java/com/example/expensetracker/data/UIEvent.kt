package com.example.expensetracker.data

import java.util.Date

sealed class UIEvent {
    data class TypeOfExpenseChanged(val typeOfExpense: String) : UIEvent()
    data class NameChanged(val name: String) : UIEvent()
    data class CategoryChanged(val category: String) : UIEvent()
    data class AmountChanged(val amount: Double) : UIEvent()
    data class DateChanged(val date: Date) : UIEvent()
    data class IncomeAmountChanged(val amount: Double) : UIEvent()
    data class ExpenseAmountChanged(val amount: Double) : UIEvent()
    data class TotalAmountChanged(val amount: Double) : UIEvent()

    data object AddExpenseBtnClicked : UIEvent()
}