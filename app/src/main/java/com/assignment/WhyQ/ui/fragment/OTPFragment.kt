package com.assignment.WhyQ.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.assignment.WhyQ.R
import com.assignment.WhyQ.databinding.FragmentOtpBinding
import com.assignment.WhyQ.ui.activity.DashboardActivity
import com.assignment.WhyQ.ui.constants.TOKEN
import com.assignment.WhyQ.ui.constants.USER_ID
import com.assignment.WhyQ.ui.repository.AuthRepository
import com.assignment.WhyQ.ui.util.PreferenceManager
import com.assignment.WhyQ.ui.util.Resource
import com.assignment.WhyQ.ui.viewmodel.AuthViewModel
import com.assignment.WhyQ.ui.viewmodel.AuthViewModelProviderFactory


class OTPFragment : Fragment(R.layout.fragment_otp) {
    var binding: FragmentOtpBinding? = null
    val authViewModel by lazy {
        val authRepository = AuthRepository()
        val viewModelProviderFactory  = AuthViewModelProviderFactory(authRepository)
        ViewModelProvider(requireActivity(), viewModelProviderFactory ).get(AuthViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOtpBinding.bind(view)
        setObserver()
        setListeners()
    }

    private fun setListeners() {
        binding?.btnBack?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding?.btnDone?.setOnClickListener {
            if((binding?.otpView?.text?.length ?: 0) < 6){
                binding?.otpView?.setError("Please enter 6 digit OTP.")
                return@setOnClickListener
            }
            authViewModel.verifyOtp(binding?.otpView?.text?.toString()!!)
        }
    }

    private fun setObserver() {
        authViewModel.mobileNumber.observe(viewLifecycleOwner){mobNumber->
            binding?.let {
                val text =
                    "Enter the code we have sent to <font color=#08454C>+60$mobNumber </font> via SMS."
                it.tvMsg.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
            }
        }
        authViewModel.isOtpVerified.observe(viewLifecycleOwner){
            it?.let {
                if(it){
                    authViewModel.confirmOtp();
                }else{
                    binding?.otpView?.setError("You have entered Wrong OTP.")
                }
                authViewModel.clearVerifyOtpData()
            }
        }
        authViewModel.confirmOtpResponse.observe(viewLifecycleOwner){
            it?.let {
                when(it){
                    is Resource.Success -> {
                        hideLoadingState()
                        authViewModel.clearOtpResponse()
                        val preferenceManager = PreferenceManager(requireContext())
                        preferenceManager.saveStringData(TOKEN, it.data?.data?.token!!)
                        preferenceManager.saveStringData(USER_ID, it.data.data?.userId!!)
                        val intent = Intent(requireActivity(), DashboardActivity::class.java)
                        startActivity(intent)
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
    }

    private fun showLoadingState() {
        binding?.btnDone?.visibility = View.GONE
        binding?.progress?.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        binding?.btnDone?.visibility = View.VISIBLE
        binding?.progress?.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}