package com.example.example

import com.google.gson.annotations.SerializedName


data class OrdersData (

  @SerializedName("orders" ) var orders : ArrayList<Orders> = arrayListOf()

)