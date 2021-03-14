package com.deanezra.network

import com.deanezra.network.model.PlantsResponse
import retrofit2.Response

interface PlantDataApiHelper {
    suspend fun getPlants(): Response<PlantsResponse>
}