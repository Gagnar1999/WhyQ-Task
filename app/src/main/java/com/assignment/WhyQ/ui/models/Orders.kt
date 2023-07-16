package com.example.example

import com.google.gson.annotations.SerializedName


data class Orders (

  @SerializedName("id"                       ) var id                    : String?          = null,
  @SerializedName("cluster_id"               ) var clusterId             : String?          = null,
  @SerializedName("total_qty"                ) var totalQty              : Int?             = null,
  @SerializedName("order_sub_total"          ) var orderSubTotal         : String?          = null,
  @SerializedName("order_unique_id"          ) var orderUniqueId         : String?          = null,
  @SerializedName("order_status"             ) var orderStatus           : String?          = null,
  @SerializedName("partner_id"               ) var partnerId             : String?          = null,
  @SerializedName("delivery_type"            ) var deliveryType          : String?          = null,
  @SerializedName("takeaway_charge_per_item" ) var takeawayChargePerItem : String?          = null,
  @SerializedName("is_parcel_delivery"       ) var isParcelDelivery      : String?          = null,
  @SerializedName("order_total"              ) var orderTotal            : String?          = null,
  @SerializedName("order_date"               ) var orderDate             : String?          = null,
  @SerializedName("payment_method"           ) var paymentMethod         : String?          = null,
  @SerializedName("payment_status"           ) var paymentStatus         : String?          = null,
  @SerializedName("created_at"               ) var createdAt             : String?          = null,
  @SerializedName("name"                     ) var name                  : String?          = null,
  @SerializedName("isActionButtonDisable"    ) var isActionButtonDisable : Int?             = null,
  @SerializedName("orderSource"              ) var orderSource           : String?          = null,
  @SerializedName("orderSourceId"            ) var orderSourceId         : String?          = null,
  @SerializedName("deliveryTypeLabel"        ) var deliveryTypeLabel     : String?          = null,
  @SerializedName("trans_proof"              ) var transProof            : String?          = null,
  @SerializedName("orderDeliverTime"         ) var orderDeliverTime      : String?          = null,
  @SerializedName("tracking_link"            ) var trackingLink          : String?          = null,
  @SerializedName("delivery_courier"         ) var deliveryCourier       : String?          = null,
  @SerializedName("payment_method_text"      ) var paymentMethodText     : String?          = null,
  @SerializedName("extra_item_count"         ) var extraItemCount        : Int?             = null,
  @SerializedName("items"                    ) var items                 : ArrayList<Items> = arrayListOf(),
  @SerializedName("item_string"              ) var itemString            : String?          = null

)