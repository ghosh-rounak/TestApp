package com.rounak.testapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rounak.testapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("some changes")
    }
}

//validation functions for forms
//dao functions
//viewmodel validation function with live data
// testing navigation of fragments on button clicks and corresponding back press navigation.
// testing functionality (like popback on back press and and comparing a value set in viewmodel) on click of a recycler view item. Disable rv animation before this temporarily
// testing total ui form functionality with espresso. Like testing of Add Item Form UI functionality along with local database.
// Testing delete an item for UI recycler view list along with local db.