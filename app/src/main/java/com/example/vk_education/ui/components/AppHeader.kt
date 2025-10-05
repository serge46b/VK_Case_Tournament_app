package com.example.vk_education.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vk_education.data.ApiClient
import com.example.vk_education.utils.APKInstall
import com.example.vk_education.utils.APKInstallViewModel

@Composable
fun AppHeader(
    appName: String,
    publisher: String,
    appIcon: String,
    appHeader: String,
    category: String,
    apkUrl: String,
    rating: Double,
    ageRating: String,
    downloads: String,
    size: Double,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val apkInstaller = APKInstall(context)
    val apkInstallerViewModel: APKInstallViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return APKInstallViewModel(apkInstaller) as T
            }
        }
    )
    val progress = remember { mutableStateOf(0.0) }
    val isSuccess = remember { mutableStateOf(false) }
    val isInProgress = remember { mutableStateOf(false) }

    Box {
        // App Header Image (full width, responsive height)
        AsyncImage(
            model = ApiClient.DOMAIN + appHeader,
            contentDescription = "App Header",
            modifier = Modifier
                .fillMaxWidth()
                .height(146.dp)
        )

        // Content Column positioned to overlay the header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 110.dp), // Position to overlay header by half
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalAlignment = Alignment.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(8.dp),

                verticalAlignment = Alignment.CenterVertically,
            ) {
                // App Icon - positioned to overlay header by half
                AsyncImage(
                    model = ApiClient.DOMAIN + appIcon,
                    contentDescription = appName,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp))
                    //.background(Color.Blue
                )

                Spacer(modifier = Modifier.width(8.dp))

                // App Info Column
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(0.25.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    // App Name
                    Text(
                        text = appName,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        maxLines = 1
                    )

                    // Publisher
                    Text(
                        text = publisher,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        color = Color.Gray,
                        maxLines = 1
                    )
                    Text(
                        text = category,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        color = Color(0xFF8024C0),
                        maxLines = 1

                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Download Button (responsive width, 48dp height)
            DefaultButton(
                onClick = {
                    if (isSuccess.value || isInProgress.value) return@DefaultButton
                    val apkUri = "http://auth.mofius-server.ru${apkUrl}"
                    isInProgress.value = true
                    apkInstallerViewModel.downloadAPKAsync(apkUri, onProgress = { c, m, s ->
                        progress.value = c * 100.0 / m
                        Log.i("Installation", "Installation progress $c/$m")
                    }, onComplete = { r ->
                        isSuccess.value = r
                        isInProgress.value = false
                        Log.i("Installation", "Installation ended")
                    })
                },
                text = if (!isInProgress.value) {if (isSuccess.value) "Скачано" else "Скачать"} else "Загрузка: ${"%.2f".format(progress.value)}",
                width = 345, // Keep fixed width for button consistency
                height = 48,
                backgroundColor = Color.Blue,
                textColor = Color.White,
                fontSize = 16
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Rating, Age Rating, Size and Downloads Row (responsive width, 40dp height)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = com.example.vk_education.R.drawable.reviewstar),
                        contentDescription = "Review star",
                        modifier = Modifier.size(8.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = rating.toString(),
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = Color.Black,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Divider
                Text(
                    text = "|",
                    fontSize = 14.sp,
                    color = Color(0xFFD9D9D9),
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Age Rating
                Text(
                    text = ageRating,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Divider
                Text(
                    text = "|",
                    fontSize = 14.sp,
                    color = Color(0xFFD9D9D9),
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.width(12.dp))

                // App Size
                Text(
                    text = "${size} МБ",
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Divider
                Text(
                    text = "|",
                    fontSize = 14.sp,
                    color = Color(0xFFD9D9D9),
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.width(12.dp))

                // Downloads
                Text(
                    text = downloads,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun AppHeaderPreview(){
//    Box(
//        modifier = Modifier
//            .background(Color.White)
//    ) {
//        AppHeader(
//            appName = "Название приложения",
//            publisher = "Разработчик",
//            appIcon = android.R.drawable.ic_dialog_info,
//            appHeader = android.R.drawable.ic_dialog_info,
//            rating = 4.8F,
//            ageRating = 6,
//            downloads = "1M+",
//            size = 25.5F,
//            onClick = { /* Preview action */ }
//        )
//    }
//}