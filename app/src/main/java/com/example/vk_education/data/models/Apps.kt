package com.example.vk_education.data.models

import com.google.gson.annotations.SerializedName

data class AppPreview(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("company") val company: String,
    @SerializedName("short_description") val shortDescription : String,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("header_image_url") val headerImageUrl: String,
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("age_rating") val ageRating: String,
    @SerializedName("rating") val rating: Double
)
