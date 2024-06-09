package com.example.expensetracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.R
import com.example.expensetracker.ui.theme.tealLight1
import com.example.expensetracker.ui.theme.tealLight5
import com.example.expensetracker.ui.theme.tealLight6
import com.example.expensetracker.ui.theme.tealLight7

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
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
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TopButtons(
                    startIcon = R.drawable.ic_back,
                    centerText = "Profile",
                    endIcon = R.drawable.dots_menu,
                    navController = navController
                )
                Row(
                    Modifier
                        .padding(top = 80.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(percent = 50))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dp5),
                            contentDescription = "Profile Picture",
                        )
                    }
                }
            }
        }
        // After this the card ends add border to see
        NamePlate()
        Spacer(modifier = Modifier.padding(0.dp))
        ProfileListItem(imageId = R.drawable.diamond, title = "Invite Friends")
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        LazyColumn {
            itemsIndexed(profileItemList) { index, item ->
                ProfileListItem(item.imageId, item.title)
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}

@Composable
fun ProfileListItem(
    imageId: Int = R.drawable.filled_person_24,
    title: String = "Account Info"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(end = 24.dp)
                .background(
                    color = tealLight6,
                    shape = RoundedCornerShape(50)
                )
                .size(40.dp)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "diamond"
            )
        }
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
