package com.example.vk_education.ui.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vk_education.ui.components.AppCardBig
import com.example.vk_education.ui.components.CategoryList
import com.example.vk_education.ui.viewmodels.AppsPreviewListViewModel


@Composable
fun HomePage(
    onAppClick: (Int) -> Unit = {}
){
    val viewModel: AppsPreviewListViewModel = viewModel()
    val appsPreviewList = viewModel.appsPreviewList.value
    val appsCategoriesList = viewModel.categoryList.value
    val topData = appsPreviewList.find { ap -> ap.id == 1 }

    if (viewModel.isLoading) {
        Text("Loading.......")
        return
    }

    if (viewModel.error != null) {
        Text(viewModel.error!!)
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 50.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (topData != null) AppCardBig(
            topData.name,
            topData.company,
            topData.iconUrl,
            topData.headerImageUrl,
            topData.rating,
            topData.ageRating,
            onClick = {onAppClick(topData.id)})
//        TODO: Add featured apps
        for (cat in appsCategoriesList) {
            CategoryList(
                title = cat.name,
                appPreviewList = appsPreviewList.filter { ap -> ap.categoryId == cat.id },
                onAppClick = onAppClick
            )
        }
    }
}
