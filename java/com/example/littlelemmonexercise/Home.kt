package com.example.littlelemmonexercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.Divider
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.lifecycle.lifecycleScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import com.example.littlelemmonexercise.ui.theme.LittleLemmonExerciseTheme
import androidx.compose.ui.graphics.Color
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavController,  database: AppDatabase) {

    val dbItems by database.menuDao().getAllMenuItems().observeAsState(initial = emptyList())

    Column{

        Header(NavController)

        Hero(items)

    }
}


@Composable
fun Header(navController: NavController) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .padding(horizontal = 20.dp)
        )
        IconButton(onClick = { navigate(Profile.route) }) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile"

            )
        }

    }
}

@Composable
fun Hero(items: List<MenuItem>) {

    var selectedCategory by remember { mutableStateOf("") }

    Column {
        UpperPanel()
        Row {
        var searchPhrase by remember { mutableStateOf("") }

        OutlinedTextField(
            label = { Text(text = "Enter search") },
            value = searchPhrase,
            onValueChange = { searchPhrase = it }
            leadingIcon = {
                Icon(
                    Icons.Default.Search, contentDescription = "Search"
                )
            },
        )
        if (searchPhrase.isNotEmpty()) {
           items =
               items.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        }
    }
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "ORDER FOR DELIVERY!",
                modifier = Modifier.padding(top = 40.dp),
            )
            val scrollState = rememberScrollState()

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Button(
                    onClick = {
                        selectedCategory = "starters"
                    }, modifier = Modifier.height(30.dp)
                           .horizontalScroll(scrollState)
                ) {
                    Text(text = "Starters")
                }

                Button(
                    onClick = {
                        selectedCategory = "mains"
                    }, modifier = Modifier.height(30.dp)
                ) {
                    Text(text = "Mains")
                }

                Button(
                    onClick = {
                        selectedCategory = "desserts"
                    }, modifier = Modifier.height(30.dp)
                ) {
                    Text(text = "Desserts")
                }

                Button(
                    onClick = {
                        selectedCategory = "drinks"
                    }, modifier = Modifier.height(30.dp)
                ) {
                    Text(text = "Drinks")
                }
            }
            if (selectedCategory.isNotEmpty()) {
                items = items.filter { it.category.contains(selectedCategory) }
            }
        }
        MenuItems(items)
    }

}


@Composable
fun MenuItems(items: List<MenuItem>) {


   LazyColumn {
            items(
                items = items,
                itemContent = { item ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                        ) {
                                Column(
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                ) {
                                    Text(
                                        text = item.title,
                                        color = Color(0xFF000000),
                                    )
                                    Text(
                                        text = item.description,
                                        modifier = Modifier
                                            .padding(top = 5.dp, bottom = 5.dp)
                                            .fillMaxWidth(.75f)
                                    )
                                    Text(
                                        text = item.price,
                                        color = Color(0xFF6E756D),
                                    )
                                }
                                
                                GlideImage(
                                model = item.image,
                                contentDescription = "menu item",
                                modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                )
                                 
                            }
                        }
                }
            )

        }

}

@Composable
fun UpperPanel(){

    Column {
        Text(
            text = stringResource(id = R.string.title),
            style = MaterialTheme.typography.h3,
            color = Color(0xFFF4AB56)
        )

        Row(
            modifier = Modifier
                .padding(top = 18.dp)
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.location),
                    color = Color(0xFFF12CAA)
                )
                Text(
                    text = stringResource(id = R.string.description),
                    modifier = Modifier
                        .padding(bottom = 18.dp)
                        .fillMaxWidth(0.6f)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.heroimage),
                contentDescription = "Hero Image",
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
        }

    }
}
