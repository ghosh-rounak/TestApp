package com.rounak.testapp.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)  //specifying junit for android  test
class ValidateAddUserInputsUtilAndroidTest{
    @Test
    fun invalid_email_format_returns_false(){
        val result1 =  ValidateAddUserInputsUtil.validateEmail("")
        Truth.assertThat(result1).isFalse()

        val result2 =  ValidateAddUserInputsUtil.validateEmail(" ")
        Truth.assertThat(result2).isFalse()

        val result3 =  ValidateAddUserInputsUtil.validateEmail("rounak@")
        Truth.assertThat(result3).isFalse()
    }

    @Test
    fun valid_email_format_returns_true(){
        val result =  ValidateAddUserInputsUtil.validateEmail("rounak@gmail.com")
        Truth.assertThat(result).isTrue()
    }
}