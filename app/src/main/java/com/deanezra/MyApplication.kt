package com.deanezra

import android.app.Activity
import android.app.Application
import com.deanezra.di.components.DaggerAppComponent
import com.deanezra.di.modules.NetworkModule

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class MyApplication : Application(), HasActivityInjector {

    private val BASE_URL = "https://trefle.io"

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()

        // Inject our network dependencies:
        DaggerAppComponent.builder()
            .networkModule(NetworkModule(this, BASE_URL))
            .build()
            .inject(this)
    }
}