package com.deanezra.repository

import com.deanezra.network.PlantDataApiHelper
import javax.inject.Inject

class PlantRepository @Inject constructor(
    private val apiHelper: PlantDataApiHelper
){
    suspend fun getPlants() = apiHelper.getPlants()
}