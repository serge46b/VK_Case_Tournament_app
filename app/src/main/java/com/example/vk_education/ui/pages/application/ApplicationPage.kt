package com.example.vk_education.ui.pages.application

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vk_education.ui.viewmodels.AppInfoViewModel
import androidx.core.net.toUri
import com.example.vk_education.utils.APKInstall
import com.example.vk_education.utils.APKInstallViewModel
import kotlin.jvm.java

//import com.example.vk_education.utils.APKDownloaderInstaller


interface ApplicationPageProps {
    val appId: Int
}

@Composable
fun ApplicationPage(props: ApplicationPageProps) {
    val progress = remember { mutableStateOf(0.0) }
    val installStage = remember { mutableStateOf<String?>(null) }
    val isSuccess = remember { mutableStateOf(false) }
    val isInProgress = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val viewModel: AppInfoViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AppInfoViewModel(props.appId) as T
            }
        }
    )
    val appInfo = viewModel.appInfo.value
    val apkInstaller = APKInstall(context)
    val apkInstallerViewModel: APKInstallViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return APKInstallViewModel(apkInstaller) as T
            }
        }
    )
    Column {
        Text("Heeeyyyy~ ${props.appId} || ${viewModel.appId}", Modifier.padding(10.dp, 10.dp))
        if (viewModel.isLoading) Text("Loading...")
        else if (viewModel.error != null) Text(viewModel.error!!)
        else {
            AsyncImage(
                model = "http://auth.mofius-server.ru${appInfo!!.iconUrl}",
                contentDescription = null
            )
            Button(onClick = {
                val apkUri = "http://auth.mofius-server.ru${appInfo.apkUrl}"
                isInProgress.value = true
                apkInstallerViewModel.installAPKAsync(apkUri, onProgress = { c, m, s ->
                    progress.value = c * 100.0 / m
                    installStage.value = if (s == 0) "Download..." else "Installing..."
                    Log.i("Installation", "Installation progress $c/$m")
                }, onComplete = { r ->
                    isSuccess.value = r
                    isInProgress.value = false
                    Log.i("Installation", "Installation ended")
                })

            }) { Text(if (!isInProgress.value) "Install!" else installStage.value?:"") }
            if(isInProgress.value) Text("Progress: ${progress.value}")
            if(isSuccess.value) Text("App installed successfully")
        }
    }
}
