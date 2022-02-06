package com.rounak.testapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rounak.testapp.data.db.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun usersDao():UsersDao
}