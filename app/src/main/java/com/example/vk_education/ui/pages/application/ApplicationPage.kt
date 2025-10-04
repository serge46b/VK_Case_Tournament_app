package com.example.vk_education.ui.pages.application

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
    Column {
        Text("Heeeyyyy~ ${props.appId} || ${viewModel.appId}", Modifier.padding(10.dp, 10.dp))
        if (viewModel.isLoading) Text("Loading...")
        else if (viewModel.error != null) Text(viewModel.error!!)
        else AsyncImage(model = "http://auth.mofius-server.ru${appInfo!!.iconUrl}", contentDescription = null)
    }
}