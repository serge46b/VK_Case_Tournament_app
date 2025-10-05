package com.example.vk_education.ui.pages.application

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vk_education.ui.components.AppHeader
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
    }
}