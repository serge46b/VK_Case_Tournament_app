package com.example.vk_education.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.solrudev.ackpine.installer.PackageInstaller
import ru.solrudev.ackpine.installer.createSession
import ru.solrudev.ackpine.installer.parameters.InstallConstraints
import ru.solrudev.ackpine.installer.parameters.InstallMode
import ru.solrudev.ackpine.installer.parameters.InstallerType
import ru.solrudev.ackpine.installer.parameters.constraints
import ru.solrudev.ackpine.session.Session
import ru.solrudev.ackpine.session.await
import ru.solrudev.ackpine.session.progress
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration.Companion.minutes

class APKInstall(private val context: Context) {

    suspend fun installApkWithProgress(
        apkUri: Uri,
        onProgress: (Long, Long) -> Unit,
        onComplete: (Boolean) -> Unit
    ) {
        val packageInstaller = PackageInstaller.getInstance(context)
        val session = packageInstaller.createSession(apkUri){
            installerType = InstallerType.INTENT_BASED
            installMode = InstallMode.Full
            constraints(timeout=1.minutes){
                timeoutStrategy = InstallConstraints.TimeoutStrategy.CommitEagerly
            }
        }
        val coroutineScope = CoroutineScope(Dispatchers.Main)

        // Observe progress
        session.progress.onEach { progress ->
            onProgress(
                progress.progress.toLong(),
                progress.max.toLong()
            )
        }
            .launchIn(coroutineScope)


        // Wait for completion
        try {
            when (val result = session.await()) {
                is Session.State.Succeeded -> {
                    Log.i("Installation", "Installation succeeded")
                    onComplete(true)
                }

                is Session.State.Failed -> {
                    Log.e("Installation", "Installation failed: ${result.failure.message}")
                    onComplete(false)
                }
            }
        } catch (cancellationException: CancellationException) {
            Log.i("Installation", "Cancelled")
            onComplete(false)
            throw cancellationException
        } catch (exception: Exception) {
            Log.e("Installation", exception.toString())
            onComplete(false)
        }
    }

    private val client = OkHttpClient()

    suspend fun downloadApk(
        url: String,
        fileName: String,
        onProgress: (Long, Long) -> Unit = { _, _ -> }
    ): File? = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()

            if (!response.isSuccessful) {
                throw Exception("Failed to download: ${response.code}")
            }

            val body = response.body ?: throw Exception("Empty response body")
            val contentLength = body.contentLength()

            val file = File(context.getExternalFilesDir(null), fileName)
            val inputStream: InputStream = body.byteStream()
            val outputStream = FileOutputStream(file)

            val buffer = ByteArray(8192)
            var totalBytesRead = 0L
            var bytesRead: Int

            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
                totalBytesRead += bytesRead

                // Report progress
                onProgress(totalBytesRead, contentLength)
            }

            inputStream.close()
            outputStream.close()

            file
        } catch (e: Exception) {
            Log.e("Installation", "Download failed: ${e.message}")
            null
        }
    }
}

class APKInstallViewModel(val apkInstaller: APKInstall) : ViewModel() {

    fun downloadAPKAsync(
        apkUri: String,
        onProgress: (Long, Long, Int) -> Unit,
        onComplete: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            val file = apkInstaller.downloadApk(apkUri, "tmp.apk", { c, m -> onProgress(c, m, 0) })
            if (file !== null) onComplete(true)
        }
    }
    fun installAPKAsync(
        apkUri: String,
        onProgress: (Long, Long, Int) -> Unit,
        onComplete: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val file = apkInstaller.downloadApk(apkUri, "tmp.apk", { c, m -> onProgress(c, m, 0) })
            if (file == null) onComplete(false)
            else
                apkInstaller.installApkWithProgress(
                    file.toUri(),
                    { c, m -> onProgress(c, m, 1) },
                    { r ->
                        onComplete(r)
                        if (r) file.delete()
                    })

        }
    }
}