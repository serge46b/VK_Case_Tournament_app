package com.example.vk_education.ui.components
import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SearchBar() {
    Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F1F1))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(
                painter = painterResource(id = com.example.vk_education.R.drawable.iconsearch),
                contentDescription = "Icon search",
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Найдется все",
                color = Color(0xFF777777),
                fontSize = 15.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchBarPreview() {
//    SearchBar()
//}