package com.rounak.testapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Email")
    val email: String,

    @ColumnInfo(name = "Name") val name: String,

    @ColumnInfo(name = "Address") val address: String,

    @ColumnInfo(name = "Phone") val phone: String
)
