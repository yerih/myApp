package com.admissions.empty_project.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        launchAndCollect(viewModel.event){ event ->
            when(event){
                NavigateToDashboard -> {}
                NavigateToSignUp    -> {}
            }
        }
        launchAndCollect(viewModel.state){
            with(binding){
                viewModel.email = inputEmail.text.toString().trim()
                binding.viewModel = viewModel
            }
        }
    }

}