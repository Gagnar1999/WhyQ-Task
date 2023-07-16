package com.assignment.WhyQ.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.WhyQ.ui.mode.ConfirmOtpResponse
import com.assignment.WhyQ.ui.models.SignupResponse
import com.assignment.WhyQ.ui.repository.AuthRepository
import com.assignment.WhyQ.ui.util.Resource
import kotlinx.coroutines.launch

class AuthViewModel(val authRepository: AuthRepository) : ViewModel() {
    private val _mobileNumber = MutableLiveData<String>()
    val mobileNumber: LiveData<String>
        get() = _mobileNumber

    private val _signUpResponse = MutableLiveData<Resource<SignupResponse>?>()
    val signupResponse: LiveData<Resource<SignupResponse>?>
        get() = _signUpResponse

    private val _confirmOtpResponse = MutableLiveData<Resource<ConfirmOtpResponse>?>()
    val confirmOtpResponse: LiveData<Resource<ConfirmOtpResponse>?>
        get() = _confirmOtpResponse

    private val _isOtpVerified = MutableLiveData<Boolean?>()
    val isOtpVerified: LiveData<Boolean?>
        get() = _isOtpVerified

    private var correctOtp: String = "";

    fun onSubmitMobileNumber(mobileNumber: String) {
        _signUpResponse.postValue(Resource.Loading())
        _mobileNumber.value = mobileNumber
        viewModelScope.launch {
            val signupResponse =
                authRepository.signUp("{\"deviceType\":\"android\",\"appVersion\":\"400\",\"apiVersion\":\"2.0\",\"authKey\":\"cd5b5206057d79a8bcf5656960606a4a8b53e137c15eba5a96a17830a52ebba9\",\"deviceId\":\"fjy3MPVUQ6-wDZPaVSMDVb:APA91bEP3xMGOegQPfTiuLgeIPJ7STrz9qFG06Y9kwuGNpcSVMFJRL5uhBTabRTKrJGQtCvNb6I8Ssx0yqFkYWoMIos-ntfmTgwx8DOgu301Szex_6YEfULPBRsiCZJ0uBfEvI3QVVbO\",\"deviceUniqueId\":\"UPB1.230309.017\",\"lang\":\"en\",\"deviceDetail\":\"Pixel 6a\",\"phoneNumber\":\"$mobileNumber\",\"resendOtp\":\"0\",\"isSigned\":\"0\"}")
            if (signupResponse.isSuccessful && signupResponse.code() == 200) {
                val response = signupResponse.body()
                response?.let {
                    if (it.status == 1) {
                        correctOtp = response.otp!!
                        _signUpResponse.postValue(Resource.Success(response))
                    } else {
                        _signUpResponse.postValue(Resource.Error(response.message!!, null))
                    }
                }
            } else {
                _signUpResponse.postValue(Resource.Error("Something Went Wrong !!"))
            }
        }
    }

    fun clearSignUpRepsonse() {
        _signUpResponse.postValue(null)
    }
    fun clearOtpResponse() {
        _confirmOtpResponse.postValue(null)
    }


    fun verifyOtp(currentOtp: String) {
        print(currentOtp)
        print(correctOtp)
        if (currentOtp == correctOtp) {
            _isOtpVerified.postValue(true)
        } else {
            _isOtpVerified.postValue(false)
        }
    }

    fun clearVerifyOtpData(){
        _isOtpVerified.postValue(null)
    }

    fun confirmOtp() {
        _confirmOtpResponse.postValue(Resource.Loading())
        viewModelScope.launch {
            val confirmOtpResponse =
                authRepository.confirmOtp("{\"deviceType\":\"android\",\"appVersion\":\"400\",\"apiVersion\":\"2.0\",\"authKey\":\"cd5b5206057d79a8bcf5656960606a4a8b53e137c15eba5a96a17830a52ebba9\",\"deviceId\":\"eCn566fIRlSHX_95P9NSUR:APA91bGxWvmgzImFiu52FyLDWeDONtvQuC5yToc-K8WTCoij2es2v9VZa3JILLkKTyiWqJGTcqI5YHfBcoUw_XwoIbz1NQvfHcNZQCaCwv2wvN8F8GlZP7Mp7h3gx5JkA5NUaSk9KXjU\",\"deviceUniqueId\":\"UPB1.230309.017\",\"lang\":\"en\",\"deviceDetail\":\"Pixel 6a\",\"phone_number\":\"${mobileNumber.value}\",\"otp\":\"$correctOtp\"}")
            if (confirmOtpResponse.isSuccessful && confirmOtpResponse.code() == 200) {
                val response = confirmOtpResponse.body()
                response?.let {
                        _confirmOtpResponse.postValue(Resource.Success(response))
                    }

                }
            else {
                _confirmOtpResponse.postValue(Resource.Error("Something Went Wrong!!"))
            }
        }
    }
}