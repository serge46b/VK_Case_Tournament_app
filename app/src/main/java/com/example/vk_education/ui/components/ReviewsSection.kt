package com.example.vk_education.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReviewsSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Отзывы",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
            color = Color.Black,
            maxLines = 1
        )
        Text(
            text = "Отзывы мы не успели реализовать, хотя так хотели, поэтому тут будет акендот:\n"+ "— Почему ваши дети всё время ссорятся?\n" +
                    "— Конфликт версий, — отвечает программист.",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            textAlign = TextAlign.Left,
            color = Color.Black
        )
    }
}
