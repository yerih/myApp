package com.admissions.empty_project.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.admissions.empty_project.common.launchAndCollect
import com.admissions.empty_project.data.R
import com.admissions.empty_project.data.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var adapter = AdapterUser{viewModel.onDeleteClicked(it)}
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var binding: FragmentDashboardBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        with(binding){
            recycler.adapter = adapter
            binding.viewModel = viewModel
        }
        launchAndCollect(viewModel.state){
            with(binding){
                list = it.list
                viewModel = viewModel
            }
        }
        launchAndCollect(viewModel.event){

        }
    }

}