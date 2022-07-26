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
import com.admissions.empty_project.common.launchAndCollect
import com.admissions.empty_project.data.R
import com.admissions.empty_project.data.databinding.FragmentMainBinding
import com.admissions.empty_project.ui.MainViewModel.UiEvent.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.viewModel = viewModel
        launchAndCollect(viewModel.event){ event ->
            when(event){
                NavigateToDashboard -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToDashboardFragment())
                is NavigateToSignUp -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToSignUpFragment(viewModel.email))
            }
        }
        launchAndCollect(viewModel.state){ state ->
            with(binding){
//                if(state.email.isNotEmpty()) {
//                    viewModel?.email = state.email
//                    inputEmail.setText(state.email)
//                }else
//                    viewModel?.email = inputEmail.text.toString().trim()
                uiState = state
                viewModel?.email = inputEmail.text.toString().trim()
                binding.viewModel = viewModel
            }
        }
    }

}