package com.deanezra.di.components

import com.deanezra.MyApplication
import com.deanezra.di.modules.ActivitiesModule
import com.deanezra.di.modules.NetworkModule
import dagger.Component

@Component(
    modules = [
        ActivitiesModule::class, NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(myApplication: MyApplication)
    fun inject (networkModule: NetworkModule)

}