package com.assignment.WhyQ.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.WhyQ.ui.repository.OrdersRepository

class OrderViewModelProviderFactory(val repository: OrdersRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrdersViewModel(repository) as T
    }
}