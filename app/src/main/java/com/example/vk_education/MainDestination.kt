package com.example.vk_education

import android.app.Application
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.vk_education.data.models.AppInfo
import com.example.vk_education.ui.pages.application.ApplicationPage
import com.example.vk_education.ui.pages.home.HomePage
import com.example.vk_education.ui.pages.onboarding.OnboardingPage

sealed interface PageProps


class AppDestination(val route: String, val pageCall: @Composable (options: PageProps?) -> Unit) {
    var options: PageProps? = null

    @Composable
    fun page() {
        pageCall(options)
    }
}

val Onboarding = AppDestination("Onboarding", { OnboardingPage() })
val Home = AppDestination("Home", {HomePage(onAppClick = {})})


val Application = AppDestination("Application", pageCall = {ApplicationPage(0, {})})

//interface AppDestination{
//    val route: String
//    val page: @Composable ()-> Unit
//}
//
//
//object Home : AppDestination {
//    override val route = "Home"
//    override val page: @Composable ()-> Unit = { HomePage() }
//}
//
//object Application: AppDestination{
//    override val route = "Application"
//    override val page : @Composable ()->Unit = { }
//}