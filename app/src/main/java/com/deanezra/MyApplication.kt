package com.deanezra

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    private val BASE_URL = "https://trefle.io"

    override fun onCreate() {
        super.onCreate()
    }
}