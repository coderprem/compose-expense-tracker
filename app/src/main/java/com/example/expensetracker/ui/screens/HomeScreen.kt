package com.example.expensetracker.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.expensetracker.R
import com.example.expensetracker.data.AddExpenseViewModel
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import com.example.expensetracker.ui.theme.teal
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddExpenseViewModel = viewModel()
) {
    val context = LocalContext.current
    val expenseList = viewModel.expenseList.observeAsState().value ?: emptyList()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    val onRefresh: () -> Unit = {
        swipeRefreshState.isRefreshing = true // Set refreshing state to true
        // Call updateAmounts from within a coroutine scope
        viewModel.viewModelScope.launch {
            delay(1000)
            viewModel.updateAmounts()
            swipeRefreshState.isRefreshing = false // Set refreshing state to false when done
        }
    }
    SwipeRefresh(state = swipeRefreshState, onRefresh = onRefresh) {
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
                    Row(
                        Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            Modifier.weight(1f),
                        ) {
                            GreetingText(
                                text = "Good Morning",
                                fontSize = 14,
                                fontWeight = FontWeight(400)
                            )
                            GreetingText(
                                text = "Prem Kumar",
                                fontSize = 20,
                                fontWeight = FontWeight(700)
                            )
                        }
                        IconButton(onClick = {
                            Toast.makeText(context, "Notification icon clicked", Toast.LENGTH_SHORT)
                                .show()
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_notification),
                                contentDescription = "Menu icon"
                            )
                        }
                    }
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
                                .height(180.dp),
                            elevation = CardDefaults.cardElevation(12.dp),
                            colors = CardDefaults.cardColors(teal)
                        ) {
                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp, vertical = 24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    BalanceText(totalBalance = viewModel.totalBalanceAmount.doubleValue.toString())
                                    IconButton(onClick = {
                                        Toast.makeText(
                                            context,
                                            "Menu icon clicked",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }) {
                                        Image(
                                            painter = painterResource(id = R.drawable.dots_menu),
                                            contentDescription = "Add icon"
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ExpenseText(
                                        text = "Income",
                                        money = viewModel.incomeAmount.doubleValue.toString(),
                                        imageId = R.drawable.ic_income
                                    )
                                    ExpenseText(
                                        text = "Expense",
                                        money = viewModel.expenseAmount.doubleValue.toString(),
                                        imageId = R.drawable.ic_expense
                                    )
                                }
                            }
                        }
                    }
                }
            }
            SeeAllText()
            expenseList.let {
                if (it.isEmpty()) {
                    Text(
                        text = "No transactions yet",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
            LazyColumn {
                itemsIndexed(expenseList) { _, expense ->
                    TransactionItemCard(
                        imageId = dropDownList.find {
                            it.title == expense.type
                        }?.imageId ?: R.drawable.ic_netflix,
                        title = expense.name,
                        date = expense.date,
                        money = expense.amount.toString(),
                        income = expense.category == "Income"
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "HomeScreen")
@Composable
fun GreetingPreview() {
    ExpenseTrackerTheme {
        HomeScreen(navController = NavController(context = LocalContext.current))
    }
}