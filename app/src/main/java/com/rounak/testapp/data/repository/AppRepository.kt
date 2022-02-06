package com.rounak.testapp.data.repository

import com.rounak.testapp.data.db.UsersDao
import com.rounak.testapp.data.db.entities.User
import javax.inject.Inject


class AppRepository @Inject constructor (
    private val usersDao: UsersDao
) {

    val savedUsers = usersDao.getAllUsersSortedByName()



    suspend fun insertUser(user: User) {
        usersDao.insertUser(user)
    }
}