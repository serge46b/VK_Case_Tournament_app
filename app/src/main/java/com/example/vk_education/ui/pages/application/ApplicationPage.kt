package com.example.vk_education.ui.pages.application

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vk_education.data.ApiClient
import com.example.vk_education.ui.components.AppHeader
import com.example.vk_education.ui.components.ReviewsSection
import com.example.vk_education.ui.viewmodels.AppInfoViewModel


@Composable
fun ApplicationPage(appId: Int, goBack:()->Unit) {
    Log.i("123", "${appId}")
    val viewModel: AppInfoViewModel = viewModel()
    LaunchedEffect(appId) {
        viewModel.fetchAppData(appId)
    }
    val appInfo = viewModel.appInfo.value
    val goBackInternal = {
        viewModel.clear()
        goBack()
    }
    BackHandler(onBack = goBackInternal)
    Column (modifier = Modifier.padding(top = 15.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(horizontal = 5.dp).fillMaxWidth()){
            Button(onClick = {goBackInternal()}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F1F1))) {
                Image(
                    painter = painterResource(id = com.example.vk_education.R.drawable.reviewstar),
                    contentDescription = "Back",
                    modifier = Modifier.size(8.dp)
                )
            }
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F1F1))) {
                Image(
                    painter = painterResource(id = com.example.vk_education.R.drawable.reviewstar),
                    contentDescription = "triple dots",
                    modifier = Modifier.size(8.dp)
                )
            }
        }
        if (viewModel.isLoading || appInfo == null) Text("Loading...")
        else if (viewModel.error != null) Text(viewModel.error!!)
        else AsyncImage(model = "http://auth.mofius-server.ru${appInfo.iconUrl}", contentDescription = null)

    if (viewModel.isLoading) {
        Text("Loading...")
        return
    }

    if (viewModel.error != null) {
        Text("Error: ${viewModel.error}")
        return
    }

    if (appInfo == null) {
        Text("No app information available")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
            //.padding(24.dp),
    ) {
        AppHeader(
            appName = appInfo.name,
            publisher = appInfo.company,
            appIcon = appInfo.iconUrl,
            appHeader = appInfo.headerImageUrl,
            rating = appInfo.rating,
            ageRating = appInfo.ageRating,
            downloads = appInfo.downloads,
            size = appInfo.fileSize,
            onClick = { /* Handle download action */ }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row( modifier = Modifier
            .fillMaxWidth()
            .height(224.dp)
            .padding(start = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (cat in appInfo.screenshots) {
                AsyncImage(
                    model = ApiClient.DOMAIN + cat.imageUrl,
                    contentDescription = "Screenshot",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(112.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = appInfo.description,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Left,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Horizontal divider
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 24.dp)
                .background(Color(0xFFE0E0E0))
        )

        Spacer(modifier = Modifier.height(20.dp))

        ReviewsSection()
    }
}