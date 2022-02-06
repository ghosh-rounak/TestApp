package com.rounak.testapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rounak.testapp.data.db.entities.User

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM  users ORDER BY Name ASC")
    fun getAllUsersSortedByName(): LiveData<List<User>>
}