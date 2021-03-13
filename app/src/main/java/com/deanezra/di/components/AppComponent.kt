package com.deanezra.di.components

import com.deanezra.MyApplication
import com.deanezra.di.modules.ActivitiesModule
import com.deanezra.di.modules.NetworkModule
import com.deanezra.di.modules.RepositoryModule
import com.deanezra.repository.PlantDataRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivitiesModule::class, NetworkModule::class, RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(myApplication: MyApplication)
    fun inject(networkModule: NetworkModule)
    fun inject(plantDataRepository: PlantDataRepository)
}