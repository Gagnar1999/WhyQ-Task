package com.example.example

import com.google.gson.annotations.SerializedName


data class OrdersResponse (

  @SerializedName("next_page"            ) var nextPage           : Int?    = null,
  @SerializedName("total"                ) var total              : Int?    = null,
  @SerializedName("status"               ) var status             : Int?    = null,
  @SerializedName("pending_orders_count" ) var pendingOrdersCount : String? = null,
  @SerializedName("message"              ) var message            : String? = null,
  @SerializedName("data"                 ) var data               : OrdersData?   = OrdersData()

)