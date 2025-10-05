package com.example.vk_education.data.models

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("apps_count") val appsCount: Int,
    @SerializedName("tag") val tag: String,
    @SerializedName("tag_color") val tag_color: String
)