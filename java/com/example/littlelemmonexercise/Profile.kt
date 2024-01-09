package com.example.littlelemmonexercise

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header with image and text
        Header()

        // User information
        LazyColumn {
            item {
                Text(
                    text = "Info",
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            item {
                // Display user information retrieved from SharedPreferences
                UserInfoItem("First Name: ${sharedPreferences.getString("first_name", "")}")
                UserInfoItem("Last Name: ${sharedPreferences.getString("last_name", "")}")
                UserInfoItem("Email: ${sharedPreferences.getString("email", "")}")
            }

            item {
                // Log out button
                Button(
                    onClick = {
                        with(sharedPreferences.edit()) {
                            clear()
                            apply()
                        }
                        navController.navigate(Onboarding.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(top = 16.dp)
                        .background( Color(0xFFF4CE14))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Log out")

                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background( Color(0xFFFFFFFF))
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun UserInfoItem(info: String) {
    Text(
        text = info,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(top = 8.dp)
    )
}
