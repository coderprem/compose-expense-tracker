package com.example.expensetracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String = "",
    val name: String = "",
    val category: String = "",
    val amount: Double = 0.0,
    val date: Date = Date.from(Instant.now())
)
