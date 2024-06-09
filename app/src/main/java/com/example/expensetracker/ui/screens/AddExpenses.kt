package com.example.expensetracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.R
import com.example.expensetracker.data.AddExpenseViewModel
import com.example.expensetracker.data.UIEvent
import com.example.expensetracker.models.Expense
import com.example.expensetracker.ui.theme.teal

@Composable
fun AddExpenses(
    modifier: Modifier = Modifier,
    viewModel: AddExpenseViewModel = viewModel(),
    onAddExpense: (Expense) -> Unit = {},
    navController: NavHostController
) {
    val context = LocalContext.current
    var value by remember {
        mutableStateOf(Expense())
    }
    var amount by remember {
        mutableDoubleStateOf(0.0)
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_topbar),
                contentDescription = "App top bar",
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TopButtons(
                    startIcon = R.drawable.ic_back,
                    centerText = "Add Expenses",
                    endIcon = R.drawable.dots_menu,
                    navController = navController
                )
                Row(
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .height(460.dp)
                    ) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .padding(horizontal = 16.dp, vertical = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            DropdownMenuDemo {
                                value = value.copy(type = it)
                                viewModel.handleEvent(UIEvent.TypeOfExpenseChanged(it))
                            }
                            ExpenseTextField(label = "Name") {
                                value = value.copy(name = it)
                                viewModel.handleEvent(UIEvent.NameChanged(it))
                            }
                            ExpenseTextField(label = "Amount", kbType = KeyboardType.Number) {
                                value = value.copy(amount = it.toDouble())
                                amount = it.toDouble()
                                viewModel.handleEvent(UIEvent.AmountChanged(it.toDouble()))
                            }
                            DatePickerDemo {
                                value = value.copy(date = it)
                                viewModel.handleEvent(UIEvent.DateChanged(it))
                            }
                            CategoryDropdownMenuDemo {
                                value = value.copy(category = it)
                                viewModel.handleEvent(UIEvent.CategoryChanged(it))
                            }
                            FilledTonalButton(
                                onClick = {
                                    onAddExpense(value)
                                    viewModel.handleEvent(UIEvent.AddExpenseBtnClicked)
                                    navController.popBackStack()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = teal
                                )
                            ) {
                                Text(
                                    text = "Add Expense",
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        // After this the card ends add border to see
    }
}


@Preview(showBackground = true)
@Composable
fun AddExpensesPreview() {
    val navController = rememberNavController()
    AddExpenses(navController = navController)
}
