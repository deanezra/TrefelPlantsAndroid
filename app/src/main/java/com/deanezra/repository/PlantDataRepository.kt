package com.deanezra.repository

import com.deanezra.network.PlantDataApiService

class PlantDataRepository(private val plantDataApiService: PlantDataApiService) {
    fun getPlants() = this.plantDataApiService.getPlants()
}