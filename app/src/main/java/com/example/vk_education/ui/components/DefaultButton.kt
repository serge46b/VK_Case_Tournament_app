package com.example.vk_education.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    text: String,
    width: Int,
    height: Int,
    backgroundColor: Color = Color.Blue,
    textColor: Color = Color.White,
    fontSize: Int? = null
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(width.dp)
            .height(height.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = if (fontSize != null) fontSize.sp else 14.sp,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}