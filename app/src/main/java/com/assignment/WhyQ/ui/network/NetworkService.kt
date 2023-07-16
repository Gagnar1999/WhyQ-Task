package com.assignment.WhyQ.ui.network

import com.assignment.WhyQ.ui.mode.ConfirmOtpResponse
import com.assignment.WhyQ.ui.models.SignupResponse
import com.example.example.OrdersResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {
    @FormUrlEncoded
    @POST("user/signup")
    suspend fun signUp(
        @Field("reqObject") requestObj : String
    ) : Response<SignupResponse>


    @FormUrlEncoded
    @POST("user/confirmOtp")
    suspend fun confirmOtp(
        @Field("reqObject") requestObj : String
    ) : Response<ConfirmOtpResponse>


    @FormUrlEncoded
    @POST("user/getOrders")
    suspend fun getOrders(
        @Header("Authorization") autorization : String,
        @Field("reqObject") requestObj : String
    ) : Response<OrdersResponse>


}