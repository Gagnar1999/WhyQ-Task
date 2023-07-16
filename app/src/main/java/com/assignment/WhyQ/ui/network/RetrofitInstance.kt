package com.assignment.WhyQ.ui.network

import com.assignment.WhyQ.ui.constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        val api by lazy {
            retrofit.create(NetworkService::class.java)
        }
    }
}