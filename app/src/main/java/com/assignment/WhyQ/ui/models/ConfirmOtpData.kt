package com.assignment.WhyQ.ui.models
import com.google.gson.annotations.SerializedName


data class ConfirmOtpData (

    @SerializedName("id"                  ) var id                : String? = null,
    @SerializedName("user_type"           ) var userType          : String? = null,
    @SerializedName("first_name"          ) var firstName         : String? = null,
    @SerializedName("fb_id"               ) var fbId              : String? = null,
    @SerializedName("image"               ) var image             : String? = null,
    @SerializedName("last_name"           ) var lastName          : String? = null,
    @SerializedName("email"               ) var email             : String? = null,
    @SerializedName("phone_number"        ) var phoneNumber       : String? = null,
    @SerializedName("token"               ) var token             : String? = null,
    @SerializedName("user_id"             ) var userId            : String? = null,
    @SerializedName("can_order"           ) var canOrder          : Int?    = null,
    @SerializedName("is_hawker"           ) var isHawker          : Int?    = null,
    @SerializedName("lang"                ) var lang              : String? = null,
    @SerializedName("vendor_logo"         ) var vendorLogo        : String? = null,
    @SerializedName("stall_id"            ) var stallId           : String? = null,
    @SerializedName("vendor_website_link" ) var vendorWebsiteLink : String? = null,
    @SerializedName("vendor_qr_code"      ) var vendorQrCode      : String? = null,
    @SerializedName("share_text"          ) var shareText         : String? = null,
    @SerializedName("document_uploaded"   ) var documentUploaded  : String? = null,
    @SerializedName("is_verified"         ) var isVerified        : String? = null,
    @SerializedName("total_menu"          ) var totalMenu         : String? = null,
    @SerializedName("whatsapp_text"       ) var whatsappText      : String? = null,
    @SerializedName("whatsapp_no"         ) var whatsappNo        : String? = null

)