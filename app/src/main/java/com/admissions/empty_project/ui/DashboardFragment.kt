package com.admissions.empty_project.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.admissions.empty_project.common.launchAndCollect
import com.admissions.empty_project.data.R
import com.admissions.empty_project.data.databinding.FragmentDashboardBinding
import com.admissions.empty_project.ui.DashboardViewModel.UiEvent.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var adapter = AdapterUser{ user, pos -> mViewModel.onDeleteClicked(user, pos)}
    private val mViewModel: DashboardViewModel by viewModels()
    private lateinit var binding: FragmentDashboardBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        with(binding){
            recycler.adapter = adapter
            binding.viewModel = mViewModel
        }
        launchAndCollect(mViewModel.state){
            with(binding){
                image = it.image
                list = it.list
                viewModel = mViewModel
            }
        }
        launchAndCollect(mViewModel.event){ event ->
            when(event){
                NavigateToSignUp -> findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToSignUpFragment(""))
            }

        }
    }

}