package com.rounak.testapp.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class RegistrationUtilTest {

    @Test
    fun `empty username returns false`(){
        val result1 = RegistrationUtil.validateRegistrationInput(
            username = "",
            password = "12345678",
            confirmPassword = "12345678"
        )

        assertThat(result1).isFalse()

        val result2 = RegistrationUtil.validateRegistrationInput(
            username = " ",
            password = "12345678",
            confirmPassword = "12345678"
        )

        assertThat(result2).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val result1 = RegistrationUtil.validateRegistrationInput(
            username = "new user",
            password = "",
            confirmPassword = "12345678"
        )
        assertThat(result1).isFalse()

        val result2 = RegistrationUtil.validateRegistrationInput(
            username = "new user",
            password = " ",
            confirmPassword = "12345678"
        )
        assertThat(result2).isFalse()
    }

    @Test
    fun `empty confirm password returns false`(){
        val result1 = RegistrationUtil.validateRegistrationInput(
            username = "new user",
            password = "12345678",
            confirmPassword = ""
        )
        assertThat(result1).isFalse()

        val result2 = RegistrationUtil.validateRegistrationInput(
            username = "new user",
            password = "12345678",
            confirmPassword = " "
        )
        assertThat(result2).isFalse()
    }

    @Test
    fun `password length less than 8 returns false`(){
        val result1 = RegistrationUtil.validateRegistrationInput(
            username = "new user",
            password = "1234567",
            confirmPassword = "1234567"
        )
        assertThat(result1).isFalse()

        val result2 = RegistrationUtil.validateRegistrationInput(
            username = "new user",
            password = "1234567",
            confirmPassword = "12345678"
        )
        assertThat(result2).isFalse()
    }

    @Test
    fun `confirmPassword does not match password returns false`(){
        val result1 = RegistrationUtil.validateRegistrationInput(
            username = "new user",
            password = "12345678",
            confirmPassword = "12345679"
        )
        assertThat(result1).isFalse()

        val result2 = RegistrationUtil.validateRegistrationInput(
            username = "new user",
            password = "12345678",
            confirmPassword = "123456789"
        )
        assertThat(result2).isFalse()
    }

    @Test
    fun `username already exists returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            username = "user1",
            password = "12345678",
            confirmPassword = "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `all valid registration input returns true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            username = "user new",
            password = "12345678",
            confirmPassword = "12345678"
        )
        assertThat(result).isTrue()
    }


}