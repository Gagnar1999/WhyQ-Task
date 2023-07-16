package com.assignment.WhyQ.ui.mode

import com.assignment.WhyQ.ui.models.ConfirmOtpData
import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName


data class ConfirmOtpResponse(

    @SerializedName("data") var data: ConfirmOtpData? = ConfirmOtpData(),
    @SerializedName("new_registration") var newRegistration: Int? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("is_how_you_heard_enable") var isHowYouHeardEnable: Int? = null,
    @SerializedName("referee_list") var refereeList: JsonArray?,
    @SerializedName("message") var message: String? = null

)