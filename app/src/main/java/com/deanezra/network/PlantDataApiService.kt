package com.deanezra.network

import com.deanezra.network.model.PlantsResponse
import retrofit2.Response
import retrofit2.http.GET


interface PlantDataApiService{
    /**
     * Get the list of the Plants from the API
     */
    //https://trefle.io/api/v1/plants?token=XYZ
    @GET("/api/v1/plants")
    suspend fun getPlants(): Response<PlantsResponse>

}
