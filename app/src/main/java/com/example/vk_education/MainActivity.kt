package com.example.vk_education

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.vk_education.ui.pages.application.ApplicationPageProps
import com.example.vk_education.ui.pages.home.HomePage
import com.example.vk_education.ui.pages.onboarding.OnboardingPage
import com.example.vk_education.ui.theme.VK_EducationTheme
import com.example.vk_education.utils.OnboardingUtils

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
        val context = LocalContext.current
        var currentPage: AppDestination by remember { mutableStateOf(Onboarding) }
        var isInitialized by remember { mutableStateOf(false) }

        // Check onboarding state on app start
        LaunchedEffect(Unit) {
            val hasSeenOnboarding = OnboardingUtils.hasOnboardingBeenShown(context)
            if (hasSeenOnboarding) {
                currentPage = Home
            }
            isInitialized = true
        }

        if (!isInitialized) {
            // Show loading or splash screen while checking state
            return@VK_EducationTheme
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when (currentPage) {
                Onboarding -> {
                    OnboardingPage(
                        onClick = {
                            OnboardingUtils.setOnboardingShown(context)
                            currentPage = Home
                        }
                    )
                }

                Home -> {
                    HomePage(
                        onAppClick = { appId ->
                            Application.options = ApplicationPagePropsValue(object : ApplicationPageProps {
                                override val appId: Int = appId
                            })
                            currentPage = Application
                        }
                    )
                }

                Application -> {
                    Application.page()
                }
            }
        }
    }
}
