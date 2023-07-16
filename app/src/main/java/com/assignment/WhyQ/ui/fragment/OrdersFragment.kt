package com.assignment.WhyQ.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.assignment.WhyQ.databinding.FragmentOrdersBinding
import com.assignment.WhyQ.ui.activity.DashboardActivity
import com.assignment.WhyQ.ui.adapter.OrdersAdapter
import com.assignment.WhyQ.ui.constants.TOKEN
import com.assignment.WhyQ.ui.constants.USER_ID
import com.assignment.WhyQ.ui.repository.OrdersRepository
import com.assignment.WhyQ.ui.util.InternetConnection
import com.assignment.WhyQ.ui.util.PreferenceManager
import com.assignment.WhyQ.ui.util.Resource
import com.assignment.WhyQ.ui.util.Utils
import com.assignment.WhyQ.ui.viewmodel.OrderViewModelProviderFactory
import com.assignment.WhyQ.ui.viewmodel.OrdersViewModel

class OrdersFragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by lazy {
        val viewModelFactory = OrderViewModelProviderFactory(OrdersRepository())
        ViewModelProvider(this,viewModelFactory).get(OrdersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferenceManager = PreferenceManager(requireContext())
        if(InternetConnection.hasInternetConnection(requireContext())){
            viewModel.getOrders(preferenceManager.getToken()!!, preferenceManager.getUserId()!!)
        }
        else{
            Utils.showErrorDialog(requireActivity(), "No Internet Connection")
        }
        setObserver()
    }

    private fun setObserver() {
        viewModel.ordersResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    hideLoadingState()
                    val adapter = OrdersAdapter(it.data!!.data!!.orders)
                    binding.ordersRv.adapter = adapter
                }
                is Resource.Loading->{
                    showLoadingState()
                }
                is Resource.Error->{
                    hideLoadingState()
                }
            }
        }
    }

    private fun showLoadingState() {
        binding.progressBar.visibility = VISIBLE
    }

    private fun hideLoadingState() {
        binding.progressBar.visibility = GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}