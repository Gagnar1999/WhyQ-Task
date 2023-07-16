package com.assignment.WhyQ.ui.models

class Data {
    var lang: String? = null
    var whatsapp_text: String? = null
    var whatsapp_no: String? = null
}

class SignupResponse {
    var status = 0
    var otp: String? = null
    var isAlreadySent: String? = null
    var resendOtp = 0
    var data: Data? = null
    var new_registration: String? = null
    var message: String? = null
}
