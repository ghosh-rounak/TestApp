package com.rounak.testapp.di

import android.content.Context
import androidx.room.Room
import com.rounak.testapp.data.db.AppDatabase
import com.rounak.testapp.data.db.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.rounak.testapp.constants.Constant

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesUserDao(appDatabase: AppDatabase):UsersDao = appDatabase.usersDao()

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context):AppDatabase
            = Room.databaseBuilder(context,AppDatabase::class.java, Constant.DB_NAME).build()

}