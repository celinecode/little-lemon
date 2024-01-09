package com.example.littlelemmonexercise

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController


@Composable
fun Onboarding(navController: NavController) {
    val context = LocalContext.current
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp),
       
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .padding(8.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Let´s get to know you!",
            style = MaterialTheme.typography.h1,
            color =  Color(0xFFFFFFFF),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background( Color(0xFF495E57))

        )
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(38.dp),
            )
        )

        Button(
            onClick = {
                if (areFieldsValid(firstName, lastName, email)) {
                    saveUserData(context, firstName, lastName, email)
                    navController.navigate(Home.route)
                } 
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDensity.current.run { 48.dp.toPx().toDp() })

        ) {
            Text("Register")
        }
    }
}

private fun areFieldsValid(
    firstName: String,
    lastName: String,
    email: String
): Boolean {
    return !firstName.isBlank() && !lastName.isBlank() && !email.isBlank()
}

private fun saveUserData(
    context: Context,
    firstName: String,
    lastName: String,
    email: String
) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("first_name", firstName)
        putString("last_name", lastName)
        putString("email", email)
        apply()
    }
}