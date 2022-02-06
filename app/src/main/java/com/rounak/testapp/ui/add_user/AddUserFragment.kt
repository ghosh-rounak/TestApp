package com.rounak.testapp.ui.add_user

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rounak.testapp.R
import com.rounak.testapp.databinding.FragmentAddUserBinding
import com.rounak.testapp.utils.hideKeyboard
import com.rounak.testapp.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment() {
    private val viewModel: AddUserViewModel by viewModels()
    private lateinit var binding:FragmentAddUserBinding
    private var successDialog:AlertDialog?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_user, container, false)
        setViewModelWithLifeCycleOwner()
        createObservers()
        setTextFieldChangeListeners()
        setOnClicks()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setOnClicks() {
        binding.topAppBar.setNavigationOnClickListener {
            closeKeyboard()
            requireActivity().onBackPressed()
        }
    }

    private fun createObservers() {
        viewModel.closeSoftKeyboardData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { close ->
                if(close){
                    closeKeyboard()
                }
            }
        }

        viewModel.uiMsgData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { msg ->
                if(msg.isNotBlank()){
                    requireActivity().toast(msg)
                }
            }
        }

        viewModel.uiNameErrorMsgData.observe(viewLifecycleOwner) { errorMsg ->
            if(errorMsg.isNotBlank()){
                binding.nameField.error = errorMsg
            }else{
                binding.nameField.error = null
            }
        }


        viewModel.uiEmailErrorMsgData.observe(viewLifecycleOwner) { errorMsg ->
            if(errorMsg.isNotBlank()){
                binding.emailField.error = errorMsg
            }else{
                binding.emailField.error = null
            }
        }

        viewModel.uiPhoneErrorMsgData.observe(viewLifecycleOwner) { errorMsg ->
            if(errorMsg.isNotBlank()){
                binding.phoneField.error = errorMsg
            }else{
                binding.phoneField.error = null
            }
        }

        viewModel.uiAddressErrorMsgData.observe(viewLifecycleOwner) { errorMsg->
            if(errorMsg.isNotBlank()){
                binding.addressField.error = errorMsg
            }else{
                binding.addressField.error = null
            }
        }



        viewModel.showSuccessAlertData.observe(viewLifecycleOwner) {
            it?.let { success ->
                if (success) {
                    showSuccessDialog()
                } else {
                    dismissSuccessDialog()
                }
            }
        }
    }

    private val closeKeyboard:()->Unit = { hideKeyboard() }


    private fun setViewModelWithLifeCycleOwner() {
        binding.addUserViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }




    val showSuccessDialog:()->Unit = {
        successDialog = MaterialAlertDialogBuilder(requireActivity())
            .setTitle(resources.getString(R.string.title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                viewModel.showSuccessDialog(false)
                requireActivity().onBackPressed()
            }
            .setCancelable(false)
            .show()
    }

    val dismissSuccessDialog:()->Unit = { successDialog?.dismiss()}

    override fun onDestroy() {
        super.onDestroy()
        dismissSuccessDialog()
    }


    private fun setTextFieldChangeListeners(){
        
        binding.etAddress.doAfterTextChanged {
            Log.d("onCreate", "setTextFieldChangeListeners: called")
            val address:String = it?.toString()?:""
            if(address.length>40){
                viewModel.updateAddressError("Max 40 characters allowed")
            }else if(address.isNotBlank()){
                viewModel.updateAddressError("")
            }
        }

        binding.etName.doAfterTextChanged {
            if(!viewModel.valid){
                val name:String = it?.toString()?:""
                if(name.isNotBlank()){
                    viewModel.updateNameError("")
                }else{
                    viewModel.updateNameError("Name can't be blank")
                }
            }
        }

        binding.etEmail.doAfterTextChanged {
            Log.d("onCreate", "setTextFieldChangeListeners: email changed")
            if(!viewModel.valid){
                val email:String = it?.toString()?:""
                if(viewModel.checkEmailValidity(email)){
                    viewModel.updateEmailError("")
                }else{
                    viewModel.updateEmailError("Invalid Email")
                }
            }
        }

        binding.etPhone.doAfterTextChanged {
            if(!viewModel.valid){
                val phone:String = it?.toString()?:""
                if(phone.isNotBlank()){
                    viewModel.updatePhoneError("")
                }else{
                    viewModel.updatePhoneError("Phone can't be blank")
                }
            }
        }
    }


}