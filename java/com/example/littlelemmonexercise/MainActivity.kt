package com.example.littlelemmonexercise


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.lifecycleScope
import androidx.room.Room

import com.example.littlelemmonexercise.ui.theme.LittleLemmonExerciseTheme
import io.ktor.client.plugins.contentnegotiation
import io.ktor.client.request
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       lifecycleScope.launch() {
            if (database.menuDao().isEmpty()) {
                val menuItemsNetwork = fetchMenu()
                saveMenuToDatabase(menuItemsNetwork)
            }
        }

        setContent {

            LittleLemmonExerciseTheme {

                MyNavigation(getApplicationContext(), database)

            }
        }



    }


    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetworkData>()

    }


    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItems = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuDao().saveMenuItem(menuItems)
    }

}