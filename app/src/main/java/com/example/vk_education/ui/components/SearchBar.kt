package com.example.vk_education.ui.components
import android.R.attr.onClick
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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


//@Composable
//fun SearchBar() {
//    Button(onClick = onClick) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 10.dp)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.iconsearch),
//                contentDescription = "Icon search",
//                modifier = Modifier.size(12.dp)
//            )
//            Spacer(modifier = Modifier.size(8.dp))
//            Text(
//                text = "Найдется все",
//                color = Color(0x777777),
//                fontSize = 10.sp,
//                fontWeight = FontWeight.Light
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchBarPreview() {
//    SearchBar()
//}