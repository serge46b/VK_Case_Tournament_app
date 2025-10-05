package com.example.vk_education.utils

import android.content.Context
import android.content.SharedPreferences

object OnboardingUtils {
    private const val PREFS_NAME = "onboarding_prefs"
    private const val KEY_ONBOARDING_SHOWN = "onboarding_shown"
    
    fun hasOnboardingBeenShown(context: Context): Boolean {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_ONBOARDING_SHOWN, false)
    }
    
    fun setOnboardingShown(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_ONBOARDING_SHOWN, true).apply()
    }
}
