package com.example.vk_education.data

import com.example.vk_education.data.models.AppInfo
import com.example.vk_education.data.models.AppPreview
import com.example.vk_education.data.models.Category
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    @GET("apps/")
    suspend fun getAllApps(): List<AppPreview>

    @GET("apps/?category_id={id}")
    suspend fun getAppsByCategory(@Path ("id") categoryId: Int): List<AppPreview>

    @GET("apps/search?q={query}")
    suspend fun searchApps(@Path("query") query: String): List<AppPreview>

    @GET("categories/")
    suspend fun getAllCategories(): List<Category>

    @GET("categories/?category_id={id}")
    suspend fun getCategoryById(@Path ("id") categoryId: Int): Category

    @GET("apps/{id}")
    suspend fun getAppInfoById(@Path("id") appId:Int): AppInfo
}