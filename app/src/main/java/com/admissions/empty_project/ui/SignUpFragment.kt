package com.admissions.empty_project.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.admissions.empty_project.common.launchAndCollect
import com.admissions.empty_project.common.log
import com.admissions.empty_project.data.R
import com.admissions.empty_project.data.databinding.FragmentSignUpBinding
import com.admissions.empty_project.ui.SignUpViewModel.UiEvent.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val safeArgs: SignUpFragmentArgs by navArgs()
    private val mViewModel: SignUpViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.email = safeArgs.email
        binding = FragmentSignUpBinding.bind(view)
        binding.viewModel = mViewModel
//        binding.inputEmail.setText(safeArgs.email)
        log("email safeargs = ${safeArgs.email}")
        launchAndCollect(mViewModel.event){ event ->
            when(event){
                NavigateToDashboard -> findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToDashboardFragment())
            }
        }
        launchAndCollect(mViewModel.state){ state ->
            with(binding){
                mViewModel.name  = inputName.text.toString().trim()
                mViewModel.email = inputEmail.text.toString().trim()
                mViewModel.phone = inputPhone.text.toString().trim()
                viewModel = mViewModel
                if(state.validName) layoutName.isErrorEnabled = false else layoutName.error = "field cannot be empty"
                if(state.validEmail) layoutEmail.isErrorEnabled = false else layoutEmail.error = "enter a valid email"
                if(state.validPhone) layoutPhone.isErrorEnabled = false else layoutPhone.error = "enter a valid phone"
            }
        }
    }
}