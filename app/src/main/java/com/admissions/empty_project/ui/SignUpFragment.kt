package com.admissions.empty_project.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.admissions.empty_project.common.launchAndCollect
import com.admissions.empty_project.data.R
import com.admissions.empty_project.data.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val safeArgs: SignUpFragmentArgs by navArgs()
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        binding.inputEmail.setText(safeArgs.email)
        launchAndCollect(viewModel.event){ event ->
            when(event){
                SignUpViewModel.UiEvent.NavigateToDashboard -> findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToDashboardFragment())
            }
        }
        launchAndCollect(viewModel.state){

        }
    }
}