package com.example.vk_education

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.vk_education.ui.pages.application.ApplicationPage
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
private fun App (){
    VK_EducationTheme {
        var currentPage: AppDestination by remember { mutableStateOf(Home) }
        Scaffold (topBar = {
                Row {
                    Button(onClick = {currentPage = Home}) { Text("1")}
                    Button(onClick = {currentPage = Application }) { Text("2")}
                }
            }) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                currentPage.page()
            }
        }
    }
}