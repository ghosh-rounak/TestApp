package com.rounak.testapp.ui.dashboard

import android.view.View
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.rounak.testapp.R
import com.rounak.testapp.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), Observable {

    fun moveToAddUserScreen(view: View){
        view.findNavController().navigate(R.id.action_dashboardFragment_to_addUserFragment)
    }

    fun moveToUserListScreen(view: View){
        view.findNavController().navigate(R.id.action_dashboardFragment_to_userListFragment)
    }








    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}