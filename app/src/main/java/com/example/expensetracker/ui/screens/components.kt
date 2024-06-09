package com.example.expensetracker.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.ui.theme.teal
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun BalanceText(totalBalance: String = "5000") {
    Column(
        modifier = Modifier,
    ) {
        Text(
            text = "Total Balance",
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            color = Color.White
        )
        Text(
            text = "$ $totalBalance",
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color.White
        )
    }
}

@Composable
fun CategoryDropdownMenuDemo(
    onValueChange: (String) -> Unit = {}
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Category") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                ) {
                    Text(text = selectedOption, color = teal, fontSize = 16.sp)
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                DropdownMenuItem(
                    onClick = {
                        selectedOption = "Expense"
                        expanded = false
                        onValueChange(selectedOption)
                        Toast.makeText(context, "Selected: $selectedOption", Toast.LENGTH_SHORT)
                            .show()
                    },
                    text = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Expense", fontSize = 16.sp)
                        }
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = Color.Black
                    ),
                )
                DropdownMenuItem(
                    onClick = {
                        selectedOption = "Income"
                        expanded = false
                        onValueChange(selectedOption)
                        Toast.makeText(context, "Selected: $selectedOption", Toast.LENGTH_SHORT)
                            .show()
                    },
                    text = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Income", fontSize = 16.sp)
                        }
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = Color.Black
                    ),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDemo(
    onDateSelected: (Date) -> Unit = {}
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Initialize the selectedDate with the current date
    var selectedDate by remember { mutableStateOf(calendar.time) }
    var showDialog by remember { mutableStateOf(false) }

    // Initialize datePickerState with the current date in milliseconds
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

    OutlinedButton(
        onClick = {
            showDialog = true
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate),
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(shape = MaterialTheme.shapes.medium) {
                Column(modifier = Modifier.padding(vertical = 16.dp)) {
                    DatePicker(
                        state = datePickerState
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text("Cancel")
                        }
                        TextButton(onClick = {
                            showDialog = false
                            val selectedDateInMillis = datePickerState.selectedDateMillis
                            if (selectedDateInMillis != null) {
                                selectedDate = Date(selectedDateInMillis)
                                calendar.time = selectedDate
                                Toast.makeText(
                                    context,
                                    "Selected Date: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                onDateSelected(selectedDate)
                            }
                        }) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DropdownMenuDemo(
    onValueChange: (String) -> Unit = {}
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<dropDownItem?>(null) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                ) {
                    selectedOption?.let { painterResource(id = it.imageId) }?.let {
                        Image(
                            painter = it,
                            contentDescription = selectedOption?.title,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                    }
                    Text(
                        text = selectedOption?.title ?: "Type of Expense",
                        color = teal,
                        fontSize = 15.sp
                    )
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                dropDownList.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedOption = item
                        expanded = false
                        Toast.makeText(
                            context,
                            "Selected: ${item.title}",
                            Toast.LENGTH_SHORT
                        ).show()
                        onValueChange(item.title)
                    },
                        text = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = item.imageId),
                                        contentDescription = item.title,
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(end = 8.dp)
                                    )
                                    Text(
                                        text = item.title,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ExpenseText(
    text: String = "Expense",
    money: String = "5000",
    imageId: Int = R.drawable.ic_income
) {
    Column(
        modifier = Modifier,
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = imageId), contentDescription = "Income icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color.White
            )
        }
        Text(
            text = "$ $money",
            fontSize = 20.sp,
            fontWeight = FontWeight(700),
            color = Color.White
        )
    }
}

@Composable
fun ExpenseTextField(
    label: String,
    kbType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit = {}
) {
    val localFocusManager = LocalFocusManager.current
    var value by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = kbType,
            imeAction = if (label != "Amount") ImeAction.Next else ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            if (label == "Amount") localFocusManager.clearFocus() else localFocusManager.moveFocus(
                FocusDirection.Next
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = teal,
            focusedLabelColor = teal,
            cursorColor = teal
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun GreetingText(
    text: String = "Good Morning",
    fontSize: Int = 24,
    fontWeight: FontWeight = FontWeight(700)
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        color = Color.White
    )
}


@Composable
fun NamePlate() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Prem Kumar",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            ),
            color = Color.Black,
        )
        Text(
            text = "coderprem",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
            ),
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}


@Composable
fun TopButtons(
    startIcon: Int = R.drawable.ic_back,
    centerText: String = "Text",
    endIcon: Int = R.drawable.ic_notification,
    context: Context = LocalContext.current,
    navController: NavHostController
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            Toast.makeText(context, "Back icon clicked", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }) {
            Image(
                painter = painterResource(id = startIcon),
                contentDescription = "Back icon"
            )
        }
        Text(
            text = centerText, fontSize = 18.sp, fontWeight = FontWeight(500),
            color = Color.White
        )
        IconButton(onClick = {
            Toast.makeText(context, "Notification icon clicked", Toast.LENGTH_SHORT)
                .show()
        }) {
            Image(
                painter = painterResource(id = endIcon),
                contentDescription = "Menu icon"
            )
        }
    }
}

@Composable
fun TransactionItemCard(
    imageId: Int = R.drawable.ic_netflix,
    title: String = "Netflix",
    date: Date = Date.from(Instant.now()),
    money: String = "5000",
    income: Boolean = true
) {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val formattedDate = formatter.format(date)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2F7E79),
                            Color(0xFF6BAAA3),
                            Color(0xFFA7D6CD)
                        )
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Icon",
                modifier = Modifier.size(40.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White
                )
                Text(
                    text = formattedDate,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White
                )
            }
            Text(
                text = if (income) "+$$money" else "-$$money",
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = Color.Black
            )
        }
    }
}

@Composable
fun SeeAllText(
    text: String = "Recent Transactions",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight(500)
        )
        Text(text = "See all", fontSize = 14.sp, color = teal, fontWeight = FontWeight(500))
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview(modifier: Modifier = Modifier) {
    TransactionItemCard()
}
