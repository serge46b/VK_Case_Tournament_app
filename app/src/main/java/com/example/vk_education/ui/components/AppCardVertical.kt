package com.example.vk_education.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppCardVertical(
    appName: String,
    appIcon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(80.dp)
            .height(102.dp)
            .clip(RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ))
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = appIcon),
                contentDescription = appName,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = appName,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                textAlign = TextAlign.Left,
                color = Color.Black,
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppCardVerticalPreview() {
    Box(
        modifier = Modifier
            .background(Color.Black)
    ) {
        AppCardVertical(
            appName = "Название приложения",
            appIcon = android.R.drawable.ic_dialog_info,
            onClick = { /* Preview action */ }
        )
    }
}