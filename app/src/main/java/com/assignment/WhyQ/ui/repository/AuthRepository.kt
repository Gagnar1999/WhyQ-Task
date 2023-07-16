package com.assignment.WhyQ.ui.repository

import com.assignment.WhyQ.ui.network.RetrofitInstance

class AuthRepository {
    suspend fun signUp(requestBody : String)  = RetrofitInstance.api.signUp(requestBody)
    suspend fun confirmOtp(requestBody : String)  = RetrofitInstance.api.confirmOtp(requestBody)
}