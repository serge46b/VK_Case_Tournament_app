package com.example.vk_education.ui.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vk_education.R
import com.example.vk_education.ui.components.AppCardVertical


@Composable
fun HomePage(modifier: Modifier = Modifier){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(getTestApps()) { app ->
                    AppCardVertical(
                        appName = app.name,
                        appIcon = app.icon,
                        onClick = { /* Handle app click */ }
                    )
                }
            }
        }

    }
}

// Test data for apps
private fun getTestApps() = listOf(
    TestApp("Название", R.drawable.rustore),
    TestApp("Приложение", android.R.drawable.ic_dialog_alert),
    TestApp("Игра", android.R.drawable.ic_dialog_email),
    TestApp("Настройки", android.R.drawable.ic_dialog_map),
    TestApp("Камера", android.R.drawable.ic_menu_camera),
    TestApp("Галерея", android.R.drawable.ic_menu_gallery),
    TestApp("Музыка", android.R.drawable.ic_menu_slideshow),
    TestApp("Видео", android.R.drawable.ic_menu_manage)
)

data class TestApp(
    val name: String,
    val icon: Int
)

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage()
}