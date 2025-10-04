package com.example.vk_education.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import com.example.vk_education.ui.components.DefaultButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppCardBig(
    appName: String,
    publisher: String,
    appIcon: Int,
    appHeader: Int,
    rating: Float,
    ageRating: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(345.dp)
            .height(224.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Column {
            Image(
                painter = painterResource(id = appHeader),
                contentDescription = "Header Image",
                modifier = Modifier
                    .width(345.dp)
                    .height(168.dp)
                    .clip(RoundedCornerShape(
                        topStart = 8.dp, topEnd = 8.dp,
                        bottomStart = 0.dp, bottomEnd = 0.dp)
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(
                        topStart = 0.dp, topEnd = 0.dp,
                        bottomStart = 8.dp, bottomEnd = 8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = appIcon),
                    contentDescription = appName,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    // App Name (Roboto Medium 10)
                    Text(
                        text = appName,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        color = Color.Black,
                        maxLines = 1
                    )
                    
                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = publisher,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                        fontSize = 8.sp,
                        color = Color.Gray,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Rating (like AppCardHorizontal)
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = com.example.vk_education.R.drawable.reviewstar),
                                contentDescription = "Review star",
                                modifier = Modifier.size(8.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = rating.toString(),
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Light,
                                fontSize = 8.sp,
                                color = Color.Black,
                                maxLines = 1
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(4.dp))
                        
                        // Vertical bar separator
                        Text(
                            text = "|",
                            fontSize = 8.sp,
                            color = Color(0xFFD9D9D9),
                            fontWeight = FontWeight.Normal
                        )
                        
                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "${ageRating}+",
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Light,
                            fontSize = 8.sp,
                            color = Color.Black,
                        )
                    }
                }
                
                // Download Button
                DefaultButton(
                    onClick = { /* Download action */ },
                    text = "Скачать",
                    width = 95,
                    height = 32,
                    backgroundColor = Color(0xFFF1F1F1),
                    textColor = Color.Blue,
                    fontSize = 10
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppCardBigPreview() {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        AppCardBig(
            appName = "Название приложения",
            appIcon = android.R.drawable.ic_dialog_info,
            appHeader = android.R.drawable.ic_dialog_info,
            publisher = "Разработчик",
            rating = 4.8F,
            ageRating = 6,
            onClick = { /* Preview action */ }
        )
    }
}