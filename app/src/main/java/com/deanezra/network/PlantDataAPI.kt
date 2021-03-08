package com.deanezra.network

import com.deanezra.network.model.BasePlants
import retrofit2.Call
import retrofit2.http.GET


interface PlantDataAPI{
    /**
     * Get the list of the Plants from the API
     */
    //https://trefle.io/api/v1/plants?token=XYZ
    @GET("/api/v1/plants")
    fun getPlants(): Call<BasePlants>

}
