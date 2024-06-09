package com.example.expensetracker.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.expensetracker.models.Expense

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense ORDER BY date DESC")
    fun getAllExpense() : LiveData<List<Expense>>

    @Query("SELECT * FROM Expense WHERE id = :id")
    fun getExpenseById(id : Int) : LiveData<Expense>

    @Insert
    fun addExpense(expense : Expense)

    @Delete
    fun deleteExpense(expense : Expense)

    @Query("SELECT SUM(amount) FROM Expense WHERE category = 'Income'")
    suspend fun getTotalIncome(): Double?

    @Query("SELECT SUM(amount) FROM Expense WHERE category = 'Expense'")
    suspend fun getTotalExpense(): Double?
}