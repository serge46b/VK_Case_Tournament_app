package com.example.vk_education

import androidx.compose.runtime.Composable
import com.example.vk_education.ui.pages.application.ApplicationPage
import com.example.vk_education.ui.pages.home.HomePage

interface AppDestination{
    val route: String
    val page: @Composable () -> Unit
}


object Home : AppDestination {
    override val route = "Home"
    override val page: @Composable ()-> Unit = { HomePage() }
}

object Application: AppDestination{
    override val route = "Application"
    override val page : @Composable ()->Unit = { ApplicationPage() }
}