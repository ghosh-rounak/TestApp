package com.rounak.testapp.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.rounak.testapp.R
import com.rounak.testapp.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        setViewModelWithLifeCycleOwner()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setViewModelWithLifeCycleOwner() {
        binding.dashboardViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

}