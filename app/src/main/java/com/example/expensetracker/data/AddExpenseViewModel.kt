package com.example.expensetracker.data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.MainApplication
import com.example.expensetracker.models.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.Date

class AddExpenseViewModel : ViewModel() {

    private val _uiState = mutableStateOf(RegistrationUIState())
    val uiState: MutableState<RegistrationUIState>
        get() = _uiState

    private val expenseDao = MainApplication.database.getExpenseDao()
    val expenseList: LiveData<List<Expense>> = expenseDao.getAllExpense()
    var totalBalanceAmount = mutableDoubleStateOf(0.0)
    var incomeAmount = mutableDoubleStateOf(0.0)
    var expenseAmount = mutableDoubleStateOf(0.0)

    init {
        viewModelScope.launch {
            updateAmounts()
        }
    }


    suspend fun updateAmounts() {
        withContext(Dispatchers.IO) {
            val totalIncome = expenseDao.getTotalIncome() ?: 0.0
            val totalExpense = expenseDao.getTotalExpense() ?: 0.0

            withContext(Dispatchers.Main) {
                incomeAmount.doubleValue = totalIncome
                expenseAmount.doubleValue = totalExpense
                totalBalanceAmount.doubleValue = totalIncome - totalExpense
            }
        }
    }

    fun handleEvent(event: UIEvent) {
        when (event) {
            is UIEvent.TypeOfExpenseChanged -> {
                _uiState.value.typeOfExpense = event.typeOfExpense
                _uiState.value.typeOfExpenseError = false
                printState()
            }

            is UIEvent.NameChanged -> {
                _uiState.value.name = event.name
                _uiState.value.nameError = false
                printState()
            }

            is UIEvent.CategoryChanged -> {
                _uiState.value.category = event.category
                _uiState.value.categoryError = false
                printState()
            }

            is UIEvent.AmountChanged -> {
                _uiState.value.amount = event.amount
                _uiState.value.amountError = false
                printState()
            }

            is UIEvent.DateChanged -> {
                _uiState.value.date = event.date
                _uiState.value.dateError = false
                printState()
            }

            is UIEvent.AddExpenseBtnClicked -> {
                addExpense(getExpense())
            }

            is UIEvent.IncomeAmountChanged -> {
                _uiState.value.incomeAmount += event.amount
                printState()
            }
            is UIEvent.ExpenseAmountChanged -> {
                _uiState.value.expenseAmount += event.amount
            }
            is UIEvent.TotalAmountChanged -> {
                _uiState.value.totalAmount = event.amount
            }
        }
    }

    private fun printState() {
        Log.d("AddExpenseViewModel", "Change: ${_uiState.value}")
    }

    private fun validate(): Boolean {
        var isValid = true

        if (_uiState.value.typeOfExpense.isBlank()) {
            _uiState.value.typeOfExpenseError = true
            isValid = false
        }

        if (_uiState.value.name.isBlank()) {
            _uiState.value.nameError = true
            isValid = false
        }

        if (_uiState.value.category.isBlank()) {
            _uiState.value.categoryError = true
            isValid = false
        }

        if (_uiState.value.amount == 0.0) {
            _uiState.value.amountError = true
            isValid = false
        }

        if (_uiState.value.date == Date.from(Instant.EPOCH)) {
            _uiState.value.dateError = true
            isValid = false
        }

        return isValid
    }

    fun getExpense(): Expense {
        return Expense(
            type = _uiState.value.typeOfExpense,
            name = _uiState.value.name,
            category = _uiState.value.category,
            amount = _uiState.value.amount,
            date = _uiState.value.date,
        )
    }

    private fun resetUIState() {
        _uiState.value.typeOfExpense = ""
        _uiState.value.name = ""
        _uiState.value.category = ""
        _uiState.value.amount = 0.0
        _uiState.value.date = Date.from(Instant.now())

        _uiState.value.typeOfExpenseError = false
        _uiState.value.nameError = false
        _uiState.value.categoryError = false
        _uiState.value.amountError = false
    }

    // store info to database
    private fun addExpense(expense: Expense) {
        if (validate()) {
            // save to database
            Log.d("AddExpenseViewModel", "Expense saved: $expense")
            viewModelScope.launch(Dispatchers.IO) {
                expenseDao.addExpense(getExpense())
                withContext(Dispatchers.Main) {
                    updateAmounts()
                }
            }
        } else {
            Log.d("AddExpenseViewModel", "Validation failed")
        }
    }
}