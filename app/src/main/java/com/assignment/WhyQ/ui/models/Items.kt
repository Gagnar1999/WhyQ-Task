package com.example.example

import com.google.gson.annotations.SerializedName


data class Items (

  @SerializedName("item_qty"        ) var itemQty        : String? = null,
  @SerializedName("item_name"       ) var itemName       : String? = null,
  @SerializedName("order_item_id"   ) var orderItemId    : String? = null,
  @SerializedName("item_total"      ) var itemTotal      : String? = null,
  @SerializedName("addon_total"     ) var addonTotal     : String? = null,
  @SerializedName("discount_amount" ) var discountAmount : String? = null

)