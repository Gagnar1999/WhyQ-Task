package com.assignment.WhyQ.ui.util

import android.content.Context
import android.content.SharedPreferences
import com.assignment.WhyQ.ui.constants.TOKEN
import com.assignment.WhyQ.ui.constants.USER_ID

class PreferenceManager(val context: Context) {
    val sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE)

    fun saveStringData(key : String, value: String){
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getToken() : String? {
        return  sharedPreferences.getString(TOKEN, "")
    }

    fun getUserId() : String? {
        return  sharedPreferences.getString(USER_ID, "")
    }
}