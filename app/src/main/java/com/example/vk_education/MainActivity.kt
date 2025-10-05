package com.example.vk_education

import android.os.Bundle
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
import com.example.vk_education.ui.pages.application.ApplicationPageProps
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
                        Application.options =
                            ApplicationPagePropsValue(object : ApplicationPageProps {
                                override val appId = id
                            })
                        currentPage = Application

                    })
                }

                Application -> {
                    Application.page()
                }
            }
        }
    }
}
