package com.rounak.testapp.utils

    object RegistrationUtil {
    private val existingUsers: List<String> = listOf("user1", "user2", "user3")

    /**
     *
     *
    Invalid Registration input if any one is true:
    1. username empty
    2. password empty
    3. confirmPassword empty
    4. password length less than 8
    5. password and confirmPassword do not match
    6. username already exists

     *
     */


    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if(username.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            return false
        }

        if(password.length<8){
            return false
        }

        if(password!=confirmPassword){
            return false
        }

        if(existingUsers.contains(username)){
            return false
        }

        return true
    }

}