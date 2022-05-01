package com.rounak.testapp.ui.add_user

import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rounak.testapp.data.db.entities.User
import com.rounak.testapp.data.repository.AppRepository
import com.rounak.testapp.utils.Event
import com.rounak.testapp.utils.ValidateAddUserInputsUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), Observable {
    var valid = true  //detects validity of user inputs

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val inputPhone = MutableLiveData<String>()

    @Bindable
    val inputAddress = MutableLiveData<String>()

    private val closeSoftKeyboard = MutableLiveData<Event<Boolean>>()
    val closeSoftKeyboardData: LiveData<Event<Boolean>>
        get() = closeSoftKeyboard

    private val uiMsg = MutableLiveData<Event<String>>()
    val uiMsgData: LiveData<Event<String>>
        get() = uiMsg

    private val uiNameErrorMsg = MutableLiveData<String>()
    val uiNameErrorMsgData: LiveData<String>
        get() = uiNameErrorMsg

    private val uiEmailErrorMsg = MutableLiveData<String>()
    val uiEmailErrorMsgData: LiveData<String>
        get() = uiEmailErrorMsg

    private val uiPhoneErrorMsg = MutableLiveData<String>()
    val uiPhoneErrorMsgData: LiveData<String>
        get() = uiPhoneErrorMsg

    private val uiAddressErrorMsg = MutableLiveData<String>()
    val uiAddressErrorMsgData: LiveData<String>
        get() = uiAddressErrorMsg

    private var showSuccessAlert = MutableLiveData<Boolean>()
    val showSuccessAlertData: LiveData<Boolean>
        get() = showSuccessAlert





    internal val showSuccessDialog:(Boolean)->Unit = {showSuccessAlert.postValue(it) }


    fun addUser(view: View){
        closeSoftKeyboard.value = Event(true)//closes keyboard if open

        val name:String? = inputName.value
        val email:String? = inputEmail.value
        val phone:String? = inputPhone.value
        val address:String? = inputAddress.value

        if(validateAllUserInputs(
            name,
            email,
            phone,
            address
        )){
            //do db insertion
                viewModelScope.launch {
                    try {
                        val user = User(
                            email = email!!,
                            name = name!!,
                            address = address!!,
                            phone = phone!!
                        )
                        repository.insertUser(user)
                        Log.d("onCreate", "new user Inserted: $user")
                        showSuccessDialog(true)

                    }catch (e:Exception){
                        e.printStackTrace()
                        uiMsg.value = Event("Some Error occured during insertion")
                    }
                }

        }
    }


    val validateAllUserInputs:(
        String?,
        String?,
        String?,
        String?
    ) -> Boolean = {
        name:String?,
        email:String?,
        phone:String?,
        address:String? ->

        valid = true

        if(!ValidateAddUserInputsUtil.validateName(name)){
            uiNameErrorMsg.value = "Name can't be blank"
            valid = false
        }else{
            uiNameErrorMsg.value = ""
        }

        if(!ValidateAddUserInputsUtil.validateEmail(email)){
            uiEmailErrorMsg.value = "Invalid Email"
            valid = false
        }else{
            uiEmailErrorMsg.value = ""
        }

        if(!ValidateAddUserInputsUtil.validatePhone(phone)){
            uiPhoneErrorMsg.value = "Phone can't be blank"
            valid = false
        }else{
            uiPhoneErrorMsg.value = ""
        }

        if(!ValidateAddUserInputsUtil.validateAddress(address)){
            uiAddressErrorMsg.value = "Address can't be blank"
            valid = false
        }else{
            //address not blank
            if(!ValidateAddUserInputsUtil.validateAddressLength(address!!)){
                uiAddressErrorMsg.value = "Max 40 characters allowed"
                valid = false
            }else{
                uiAddressErrorMsg.value = ""
            }
        }

        valid
    }





    //update ui errors
    fun updateAddressError(error:String){
        uiAddressErrorMsg.value = error
    }

    fun updateNameError(error:String){
        uiNameErrorMsg.value = error
    }

    fun updateEmailError(error:String){
        uiEmailErrorMsg.value = error
    }

    fun updatePhoneError(error:String){
        uiPhoneErrorMsg.value = error
    }





    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}