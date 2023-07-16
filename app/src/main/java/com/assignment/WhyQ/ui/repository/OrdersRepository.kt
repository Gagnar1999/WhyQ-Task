package com.assignment.WhyQ.ui.repository

import com.assignment.WhyQ.ui.network.RetrofitInstance

class OrdersRepository {
    suspend fun getOrders(auth : String, requestBody : String) = RetrofitInstance.api.getOrders(auth, requestBody)
}