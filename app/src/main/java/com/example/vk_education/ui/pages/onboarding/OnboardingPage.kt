package com.example.vk_education.ui.pages.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vk_education.R
import com.example.vk_education.ui.components.DefaultButton

@Composable
fun OnboardingPage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.rustore),
                contentDescription = "RuStore Logo",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(260 .dp))
            Text(
                text = "Это - твой новый магазин приложений для Android",
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                textAlign = TextAlign.Center

            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "~ Красивая фразочка ~",
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            DefaultButton(
                onClick = { /* TODO: Add action */ },
                text = "Продолжить",
                width = 200,
                height = 48
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
        OnboardingPage()
}
