package com.rounak.testapp.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ValidateAddUserInputsUtilTest{


    @Test
    fun `empty name returns false`(){
        val result1 =  ValidateAddUserInputsUtil.validateName("")
        assertThat(result1).isFalse()

        val result2 =  ValidateAddUserInputsUtil.validateName(" ")
        assertThat(result2).isFalse()
    }

    @Test
    fun `non empty name returns true`(){
        val result =  ValidateAddUserInputsUtil.validateName("ffgf")
        assertThat(result).isTrue()
    }



    @Test
    fun `empty phone returns false`(){
        val result1 =  ValidateAddUserInputsUtil.validatePhone("")
        assertThat(result1).isFalse()

        val result2 =  ValidateAddUserInputsUtil.validatePhone(" ")
        assertThat(result2).isFalse()
    }

    @Test
    fun `non empty phone returns true`(){
        val result =  ValidateAddUserInputsUtil.validatePhone("1")
        assertThat(result).isTrue()
    }


    @Test
    fun `empty address returns false`(){
        val result1 =  ValidateAddUserInputsUtil.validateAddress("")
        assertThat(result1).isFalse()

        val result2 =  ValidateAddUserInputsUtil.validateAddress(" ")
        assertThat(result2).isFalse()
    }

    @Test
    fun `non empty address returns true`(){
        val result =  ValidateAddUserInputsUtil.validateAddress("dddddd ddddddddddddddddd ddddd dddddd dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd")
        assertThat(result).isTrue()
    }


    @Test
    fun `not blank address length more than 40 returns false`(){
        val result =  ValidateAddUserInputsUtil.validateAddressLength("dddddd ddddddddddddddddd ddddd dddddd dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd")
        assertThat(result).isFalse()
    }

    @Test
    fun `not blank address length not more than 40 returns true`(){
        val result =  ValidateAddUserInputsUtil.validateAddressLength("dfb vfvf")
        assertThat(result).isTrue()
    }


}