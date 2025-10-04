package com.example.vk_education.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_education.data.ApiClient
import com.example.vk_education.data.models.AppPreview
import com.example.vk_education.data.models.Category
import kotlinx.coroutines.launch

class AppsPreviewListViewModel: ViewModel(){
    val appsPreviewList = mutableStateOf<List<AppPreview>>(emptyList())
    val categoryList = mutableStateOf<List<Category>>(emptyList())
    private val _isLoading = mutableStateOf<Boolean>(false)
    private val _error = mutableStateOf<String?>(null)

    init {
        fetchAppPreviews()
    }
    fun fetchAppPreviews(catId: Int? = null){
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            appsPreviewList.value = emptyList<AppPreview>()
            categoryList.value = emptyList<Category>()
            try{
                val response = if (catId!=null) ApiClient.apiService.getAppsByCategory(catId) else ApiClient.apiService.getAllApps()
                appsPreviewList.value = response
                val catResponse = ApiClient.apiService.getAllCategories()
                categoryList.value = catResponse
            }catch (e: Exception){
                _error.value = e.message
            }
            _isLoading.value = false
        }
    }

    fun fetchSearchResults(searchQ: String){
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            appsPreviewList.value = emptyList<AppPreview>()
            categoryList.value = emptyList<Category>()
            try{
                val response = ApiClient.apiService.searchApps(searchQ)
                appsPreviewList.value = response
            }catch (e: Exception){
                _error.value = e.message
            }
            _isLoading.value = false
        }
    }

    val isLoading: Boolean
        get() {
            return _isLoading.value
        }
    val error: String?
        get(){
            return _error.value
        }
}