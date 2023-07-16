package com.assignment.WhyQ.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.assignment.WhyQ.R
import com.assignment.WhyQ.databinding.FragmentMobileNumberBinding
import com.assignment.WhyQ.ui.repository.AuthRepository
import com.assignment.WhyQ.ui.util.InternetConnection
import com.assignment.WhyQ.ui.util.Resource
import com.assignment.WhyQ.ui.util.Utils
import com.assignment.WhyQ.ui.viewmodel.AuthViewModel
import com.assignment.WhyQ.ui.viewmodel.AuthViewModelProviderFactory
import com.bumptech.glide.Glide
import com.iamageo.library.*

class MobileNumberFragment : Fragment(R.layout.fragment_mobile_number) {
    var binding : FragmentMobileNumberBinding? = null
    val authViewModel by lazy {
        val authRepository = AuthRepository()
        val viewModelProviderFactory  = AuthViewModelProviderFactory(authRepository)
        ViewModelProvider(requireActivity(), viewModelProviderFactory ).get(AuthViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMobileNumberBinding.bind(view);
        binding?.let {
            Glide.with(this).load(R.drawable.login_back).into(it.imageView)
        }
        setObserver()
        binding?.btnSubmit?.setOnClickListener {
            if(InternetConnection.hasInternetConnection(requireContext())){
                if(binding?.etMobileNumber?.text?.isEmpty() == true){
                    binding?.etMobileNumber?.error = "Please enter valid Number."
                    return@setOnClickListener
                }
                binding?.etMobileNumber?.text?.toString()
                    ?.let { it1 -> authViewModel.onSubmitMobileNumber(it1) }
            }
            else{
                Utils.showErrorDialog(requireActivity(), "No Internet Connection")
            }
        }
    }

    private fun setObserver() {
        authViewModel.signupResponse.observe(viewLifecycleOwner){
            it?.let {
                when(it){
                    is Resource.Success -> {
                        hideLoadingState()
                        findNavController().navigate(R.id.action_mobileNumberFragment_to_OTPFragment)
                        authViewModel.clearSignUpRepsonse()
                    }
                    is Resource.Loading->{
                        showLoadingState()
                    }
                    is Resource.Error->{
                        hideLoadingState()
                        Utils.showErrorDialog(requireActivity(), it.message!!)
                    }
                }
            }
        }
    }

    private fun showLoadingState() {
        binding?.btnSubmit?.visibility = GONE
        binding?.progress?.visibility = VISIBLE
    }

    private fun hideLoadingState() {
        binding?.btnSubmit?.visibility = VISIBLE
        binding?.progress?.visibility = GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}