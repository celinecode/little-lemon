package com.example.littlelemmonexercise.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColors = lightColors(
    primary = LittleLemmonExerciseColor.yellow,
    secondary = LittleLemmonExerciseColor.pink
)

@Composable
fun LittleLemmonExerciseTheme(darkTheme: Boolean = isSystemInDarkTheme(),
                              content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
