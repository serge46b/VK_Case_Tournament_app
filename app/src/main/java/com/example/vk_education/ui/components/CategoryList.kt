package com.example.vk_education.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vk_education.data.models.AppPreview

@Composable
fun CategoryList(
    title: String,
    appPreviewList: List<AppPreview>,
    onAppClick: (id:Int)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = title,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Color.Black,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Group all items into columns of 3
            val groupedItems = appPreviewList.chunked(3)
            
            items(groupedItems) { column ->
                LazyColumn(
                    modifier = Modifier
                        .width(344.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(column) { cap ->
                        AppCardHorizontal(
                            appName = cap.name,
                            appDescription = cap.shortDescription,
                            appIcon = cap.iconUrl, // Using default icon for now
                            rating = cap.rating,
                            onClick = { onAppClick(cap.id) }
                        )
                    }
                }
            }
        }
    }

}

//@Preview
//@Composable
//fun CategoryListPreview(){
//    // Create sample data for preview
//    val sampleApps = listOf(
//        AppPreview(
//            id = 1,
//            name = "Sample App 1",
//            shortDescription = "Description 1",
//            iconUrl = "",
//            headerImageUrl = "",
//            categoryId = 1,
//            ageRating = "6+",
//            rating = 4.5
//        ),
//        AppPreview(
//            id = 2,
//            name = "Sample App 2",
//            shortDescription = "Description 2",
//            iconUrl = "",
//            headerImageUrl = "",
//            categoryId = 1,
//            ageRating = "12+",
//            rating = 4.2
//        ),
//        AppPreview(
//            id = 3,
//            name = "Sample App 3",
//            shortDescription = "Description 3",
//            iconUrl = "",
//            headerImageUrl = "",
//            categoryId = 1,
//            ageRating = "18+",
//            rating = 4.8
//        ),
//        AppPreview(
//            id = 4,
//            name = "Sample App 4",
//            shortDescription = "Description 4",
//            iconUrl = "",
//            headerImageUrl = "",
//            categoryId = 1,
//            ageRating = "3+",
//            rating = 4.1
//        ),
//        AppPreview(
//            id = 5,
//            name = "Sample App 5",
//            shortDescription = "Description 5",
//            iconUrl = "",
//            headerImageUrl = "",
//            categoryId = 1,
//            ageRating = "6+",
//            rating = 4.6
//        ),
//        AppPreview(
//            id = 6,
//            name = "Sample App 6",
//            shortDescription = "Description 6",
//            iconUrl = "",
//            headerImageUrl = "",
//            categoryId = 1,
//            ageRating = "9+",
//            rating = 4.3
//        )
//    )
//
//    Box(
//        modifier = Modifier
//            .background(Color.White)
//    ) {
//        CategoryList(
//            title = "Название категории",
//            appPreviewList = sampleApps
//        )
//    }
//}