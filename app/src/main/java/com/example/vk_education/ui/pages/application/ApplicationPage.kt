package com.example.vk_education.ui.pages.application

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
    }
}