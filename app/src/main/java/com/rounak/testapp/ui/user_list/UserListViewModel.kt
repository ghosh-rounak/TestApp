package com.rounak.testapp.ui.user_list

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rounak.testapp.data.db.entities.User
import com.rounak.testapp.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), Observable {

    val savedUsers: LiveData<List<User>> = repository.savedUsers






    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}