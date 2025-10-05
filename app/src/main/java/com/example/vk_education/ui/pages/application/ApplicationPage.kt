package com.example.vk_education.ui.pages.application

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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


interface ApplicationPageProps {
    val appId: Int
}

@Composable
fun ApplicationPage(props: ApplicationPageProps) {
    val viewModel: AppInfoViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AppInfoViewModel(props.appId) as T
            }
        }
    )
    val appInfo = viewModel.appInfo.value
    
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