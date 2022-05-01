package com.rounak.testapp.utils

    import android.util.Patterns



object ValidateAddUserInputsUtil {

    /**
     * Name invalid if any of the following is true
     * Empty name
     */
    val validateName:(String?) -> Boolean = {
        !(it==null || it.isBlank())
    }


    /**
     * Email invalid if any of the following is true
     * Invalid email format
     *
     */
    val validateEmail:(String?) -> Boolean = {
        !(it == null || it.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(it).matches())
    }


    /**
     * Phone invalid if any of the following is true
     * Empty phone
     */
    val validatePhone:(String?) -> Boolean = {
        !(it==null || it.isBlank())
    }


    /**
     * Address invalid if any of the following is true
     * Empty address
     */
    val validateAddress:(String?) -> Boolean = {
        !(it == null || it.isBlank())
    }


    /**
     * Address invalid if any of the following is true
     *  Not Blank Address length > 40
     */
    val validateAddressLength:(String)->Boolean = {
        it.length <= 40
    }
}