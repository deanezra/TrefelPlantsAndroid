package com.deanezra.network

import com.deanezra.network.PlantDataApiHelper
import com.deanezra.network.PlantDataApiService
import com.deanezra.network.model.PlantsResponse
import retrofit2.Response
import javax.inject.Inject

class PlantDataApiHelperImpl @Inject constructor(
    private val plantDataApiService: PlantDataApiService
): PlantDataApiHelper {
    override suspend fun getPlants(): Response<PlantsResponse> = plantDataApiService.getPlants()
}