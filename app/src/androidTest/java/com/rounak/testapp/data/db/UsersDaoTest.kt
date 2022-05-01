package com.rounak.testapp.data.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.rounak.testapp.data.db.entities.User
import com.rounak.testapp.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)  //specifying junit for android  test
@SmallTest
class UsersDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule() //forces synchronous operations for junit


    private lateinit var database: AppDatabase
    private lateinit var dao: UsersDao

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = database.usersDao()
    }

    @After
    fun tearDown(){
        database.close()
    }


    //runBlockingTest -> used for testing coroutines or suspend functions
    @Test
    fun insertUser() = runBlockingTest {
        val user = User(
            email = "rounak@gmail.com",
            name = "Rounak",
            address = "test address",
            phone = "12345"
        )
        dao.insertUser(user)

        val allUsers:List<User> = dao.getAllUsersSortedByName().getOrAwaitValue()

        assertThat(allUsers).contains(user)


        //for deletion:
        //insert an item
        //then delete the inserted item
        //assertThat(allShoppingItems).doesNotContain(shoppingItemDeleted)

        //No need to test getAllUsers function

        //general checking any result example like total sum:
        //insert  3 items and then
        //assertThat(totPriceSum).isEqualTo(2 * 10f + 4 * 5.5f + ...)

    }
}