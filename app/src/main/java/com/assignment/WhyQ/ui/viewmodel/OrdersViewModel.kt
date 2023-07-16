package com.assignment.WhyQ.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.WhyQ.ui.repository.OrdersRepository
import com.assignment.WhyQ.ui.util.Resource
import com.example.example.OrdersResponse
import kotlinx.coroutines.launch

class OrdersViewModel(val repo : OrdersRepository) : ViewModel() {
    private val _ordersResponse = MutableLiveData<Resource<OrdersResponse>>()
    val ordersResponse : LiveData<Resource<OrdersResponse>>
    get() = _ordersResponse

    fun getOrders(auth : String, userId : String){
        _ordersResponse.postValue(Resource.Loading())
        var requestBody = "{\"deviceType\":\"android\",\"appVersion\":\"400\",\"apiVersion\":\"2.0\",\"authKey\":\"cd5b5206057d79a8bcf5656960606a4a8b53e137c15eba5a96a17830a52ebba9\",\"deviceId\":\"eCn566fIRlSHX_95P9NSUR:APA91bGxWvmgzImFiu52FyLDWeDONtvQuC5yToc-K8WTCoij2es2v9VZa3JILLkKTyiWqJGTcqI5YHfBcoUw_XwoIbz1NQvfHcNZQCaCwv2wvN8F8GlZP7Mp7h3gx5JkA5NUaSk9KXjU\",\"deviceUniqueId\":\"UPB1.230309.017\",\"lang\":\"en\",\"deviceDetail\":\"Pixel 6a\",\"order_status\":\"all\",\"page\":0,\"search_data\":\"\",\"userId\":\"$userId\"}"
        viewModelScope.launch {
            var ordersResponse = repo.getOrders(auth, requestBody)
            if(ordersResponse.isSuccessful && ordersResponse.code() == 200){
                var response = ordersResponse.body()
                response?.let {
                    _ordersResponse.postValue(Resource.Success(it))
                }
            }else{
                _ordersResponse.postValue(Resource.Error("Something Went Wrong!!"))
            }
        }
    }
}