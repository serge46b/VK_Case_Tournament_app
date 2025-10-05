package com.example.vk_education.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
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
fun AppCardBig(
    appName: String,
    publisher: String,
    appIcon: String,
    appHeader: String,
    apkUrl: String,
    rating: Double,
    ageRating: String,
    onClick: () -> Unit,
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
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(224.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .border(BorderStroke(2.dp, Color(0xFFF1F1F1)), shape = RoundedCornerShape(8.dp))
    ) {
        Column {
            AsyncImage(
                model = ApiClient.DOMAIN + appHeader,
                contentDescription = "Header Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 8.dp, topEnd = 8.dp,
                            bottomStart = 0.dp, bottomEnd = 0.dp
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp, topEnd = 0.dp,
                            bottomStart = 8.dp, bottomEnd = 8.dp
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ApiClient.DOMAIN + appIcon,
                    contentDescription = appName,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    // App Name (Roboto Medium 10)
                    Text(
                        text = appName,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.Black,
                        maxLines = 1
                    )

                    Text(
                        text = publisher,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        color = Color.Gray,
                        maxLines = 1
                    )

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
                                fontSize = 10.sp,
                                color = Color.Black,
                                maxLines = 1
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        // Vertical bar separator
                        Text(
                            text = "|",
                            fontSize = 10.sp,
                            color = Color(0xFFD9D9D9),
                            fontWeight = FontWeight.Normal
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = ageRating,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = Color.Black,
                        )
                    }
                }

                // Download Button
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
                    width = 150,
                    height = 32,
                    backgroundColor = Color(0xFFF1F1F1),
                    textColor = Color.Blue,
                    fontSize = 10
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AppCardBigPreview() {
//    Box(
//        modifier = Modifier
//            .background(Color.White)
//    ) {
//        AppCardBig(
//            appName = "Название приложения",
//            appIcon = "",
//            appHeader = "",
//            publisher = "Разработчик",
//            rating = 4.99,
//            ageRating = "6+",
//            onClick = { /* Preview action */ }
//        )
//    }
//}