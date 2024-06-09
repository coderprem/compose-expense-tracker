package com.example.expensetracker

import android.app.Application
import androidx.room.Room
import com.example.expensetracker.db.ExpenseDatabase


class MainApplication : Application() {

        companion object {
            lateinit var database : ExpenseDatabase
        }

        override fun onCreate() {
            super.onCreate()
            database = Room.databaseBuilder(
                applicationContext,
                ExpenseDatabase::class.java,
                ExpenseDatabase.NAME
            ).build()
        }
}