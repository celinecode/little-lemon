package com.example.littlelemmonexercise

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun MyNavigation(context: Context, database: MenuDatabase) {


    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (isUserDataAvailable(context)) {
            Home.route
        } else {
            Onboarding.route
        }
    ) {
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable(Home.route) {
            Home(navController = navController, database)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
    }
}

private fun isUserDataAvailable(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean(KEY_USER_DATA_AVAILABLE, false)
}