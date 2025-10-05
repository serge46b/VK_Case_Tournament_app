package com.example.vk_education.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient{
    const val DOMAIN = "http://auth.mofius-server.ru"
    private const val BASE_URL = "$DOMAIN/api/v1/"
    val apiService: ApiService by lazy { Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build().create(ApiService::class.java) }
}