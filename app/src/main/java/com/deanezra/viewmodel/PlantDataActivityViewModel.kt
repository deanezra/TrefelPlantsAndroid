package com.deanezra.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanezra.network.Resource
import com.deanezra.network.model.PlantsResponse
import com.deanezra.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// NOTE: Hilts @ViewModelInject is now deprecated, so we add: @HiltViewModel above class and then
// normal @inject before constructor:
@HiltViewModel
class PlantDataActivityViewModel @Inject constructor(
    private val plantRepository: PlantRepository
    ) : ViewModel() {

    private val _res = MutableLiveData<Resource<PlantsResponse>>()

    val res : LiveData<Resource<PlantsResponse>>
        get() = _res

    init {
        //getPlants()
    }

    fun getPlants()  = viewModelScope.launch {
        _res.postValue(Resource.loading(null))

        plantRepository.getPlants().let {
            if (it.isSuccessful){
                _res.postValue(Resource.success(it.body()))
            }else{
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}