package com.rounak.testapp.utils

    import android.content.Context
    import androidx.test.core.app.ApplicationProvider
    import com.google.common.truth.Truth.assertThat
    import com.rounak.testapp.R
    import org.junit.After
    import org.junit.Before
    import org.junit.Test


    class ResourceComparerTest{
        private lateinit var resourceComparer :ResourceComparer

        @Before
        fun setUp(){
            resourceComparer = ResourceComparer()
        }

        @After
        fun tearDown(){
            //clear resources if needed
        }

        @Test
        fun stringResourceSameAsGivenString_returnsTrue(){
            val context  =  ApplicationProvider.getApplicationContext<Context>()
            val result = resourceComparer.isEqual(context, R.string.app_name,"TestApp")
            assertThat(result).isTrue()
        }

        @Test
        fun stringResourceNotSameAsGivenString_returnsFalse(){
            val context  =  ApplicationProvider.getApplicationContext<Context>()
            val result = resourceComparer.isEqual(context, R.string.app_name,"Test App")
            assertThat(result).isFalse()
        }
    }