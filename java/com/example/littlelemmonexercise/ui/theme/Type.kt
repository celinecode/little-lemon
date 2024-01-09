package com.example.littlelemmonexercise.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.TextStyle
import com.example.littlelemmonexercise.ui.theme.LittleLemmonExerciseColor

val Typography = Typography(
    h1 = TextStyle(
    fontSize = 26.sp,
	fontWeight = FontWeight.Bold,
	color = LittleLemmonExerciseColor.charcoal
	),
	body = TextStyle(
		color = LittleLemmonExerciseColor.green,
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	),
	button = TextStyle(
	fontSize = 14.sp,
	fontWeight = FontWeight.Bold
	),
	caption = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 12.sp
	)
)
