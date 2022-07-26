package com.admissions.empty_project.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
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

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.let { data -> mViewModel.setImageUri(data.data.toString()) }
        }
    }
    private val safeArgs: SignUpFragmentArgs by navArgs()
    private val mViewModel: SignUpViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.email = safeArgs.email
        binding = FragmentSignUpBinding.bind(view)
        binding.viewModel = mViewModel
        launchAndCollect(mViewModel.event){ event ->
            when(event){
                NavigateToDashboard -> findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToDashboardFragment())
                NavigateToGallery -> launchGallery()
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

    private fun launchGallery(){
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(i)
    }
}