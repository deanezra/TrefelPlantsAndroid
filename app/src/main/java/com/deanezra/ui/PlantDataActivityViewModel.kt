package com.deanezra.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deanezra.di.modules.NetworkModule
import com.deanezra.network.NetworkStatus
import com.deanezra.network.model.BasePlants
import com.deanezra.repository.PlantDataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PlantDataActivityViewModel @Inject constructor(private val plantDataRepository: PlantDataRepository) : ViewModel() {

    @Inject
    lateinit var networkModule: NetworkModule

    val networkLiveData = MutableLiveData<BasePlants>()
    val statusLiveData = MutableLiveData<NetworkStatus>()

    fun fetchPlants() {

        val networkDataAPICall =
            plantDataRepository.getPlants()

        networkDataAPICall.enqueue(object : Callback<BasePlants> {

            override fun onResponse(
                call: Call<BasePlants>?,
                response: Response<BasePlants>?
            ) {
                if(response!!.body()!=null)
                     networkLiveData.value = response?.body()
                else{
                    statusLiveData.value = NetworkStatus.FAIL
                }
            }

            override fun onFailure(call: Call<BasePlants>?, t: Throwable?) {
                statusLiveData.value = NetworkStatus.FAIL
            }
        })

        if(!networkModule.isConnected) {
            // off line
            statusLiveData.value = NetworkStatus.INTERNET_CONNECTION
        }

    }

}