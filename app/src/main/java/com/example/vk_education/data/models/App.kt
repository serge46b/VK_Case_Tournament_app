package com.example.vk_education.data.models

import com.google.gson.annotations.SerializedName

data class AppInfo(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("company") val company: String,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("header_image_url") val headerImageUrl: String,
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("age_rating") val ageRating: String,
    @SerializedName("apk_url") val apkUrl: String,
    @SerializedName("file_size") val fileSize: Double,
    @SerializedName("downloads") val downloads: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("id") val id: Int,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("screenshots") val screenshots: List<AppScreenshot>
)

data class AppScreenshot(
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("order_index") val orderIndex: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("app_id") val appId: Int,
    @SerializedName("created_at") val createdAt: String,
)