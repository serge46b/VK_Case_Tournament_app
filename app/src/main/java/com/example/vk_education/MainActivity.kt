package com.example.vk_education

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vk_education.ui.pages.application.ApplicationPage
import com.example.vk_education.ui.pages.home.HomePage
import com.example.vk_education.ui.pages.onboarding.OnboardingPage
import com.example.vk_education.ui.theme.VK_EducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Preview
@Composable
private fun App() {
    VK_EducationTheme {
        var currentPage: AppDestination by remember { mutableStateOf(Onboarding) }
        var previousPage: AppDestination? by remember { mutableStateOf<AppDestination?>(null) }
        var selectedAppId: Int? by remember { mutableStateOf<Int?>(null) }


        val goBack = {
            if (previousPage != null) {
                currentPage = previousPage!!
                previousPage = null
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when (currentPage) {
                Onboarding -> {
                    OnboardingPage(
                        onClick = {
                            currentPage = Home
                        }
                    )
                }

                Home -> {
                    HomePage(onAppClick = { id ->
                        selectedAppId = id
                        currentPage = Application
                        previousPage = Home

                    })
                }

                Application -> {
                    ApplicationPage(selectedAppId!!, goBack)
                }
            }
        }
    }
}
