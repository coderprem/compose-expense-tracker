package com.example.expensetracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensetracker.models.Expense

@Database(entities = [Expense::class], version = 1)
@TypeConverters(Converters::class)
abstract class ExpenseDatabase : RoomDatabase() {

    companion object {
        const val NAME = "Expense_DB"
    }

    abstract fun getExpenseDao() : ExpenseDao

}