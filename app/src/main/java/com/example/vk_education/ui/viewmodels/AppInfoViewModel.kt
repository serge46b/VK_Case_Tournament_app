package com.example.vk_education.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_education.data.ApiClient
import com.example.vk_education.data.models.AppInfo
import kotlinx.coroutines.launch

class AppInfoViewModel: ViewModel() {
    val appInfo = mutableStateOf<AppInfo?>(null)
    private val _isLoading = mutableStateOf<Boolean>(false)
    private val _error = mutableStateOf<String?>(null)


    fun fetchAppData(appId:Int){

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            appInfo.value = null
            try{
                val response = ApiClient.apiService.getAppInfoById(appId)
                appInfo.value = response
            }catch (e: Exception){
                _error.value = e.message
            }
            _isLoading.value = false
        }
    }
    fun clear(){
        appInfo.value = null
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