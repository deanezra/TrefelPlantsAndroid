package com.deanezra.di.modules

import com.deanezra.network.PlantDataApiService
import com.deanezra.repository.PlantDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePlantDataRepository(plantDataApiService: PlantDataApiService) = PlantDataRepository(plantDataApiService)

}