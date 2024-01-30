package com.example.petsstore.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petsstore.interfaces.PetServiceApi
import com.example.petsstore.models.Pet
import com.example.petsstore.repositories.PetRepo
import com.example.petsstore.singleton.RetrofitHelper
import kotlinx.coroutines.launch

class PetViewModel: ViewModel() {
    private val petApiService = RetrofitHelper.getInstance().create(PetServiceApi::class.java)
    private val repository = PetRepo(petApiService)

    var pets by mutableStateOf(listOf<Pet>())

    init {
        fetchData()
    }
    private fun fetchData(){
        viewModelScope.launch {
            try {
                pets = repository.getAllPets()
            } catch (e: Exception){
                Log.i("GET_PETS_KEY", e.message!!)
            }

        }
    }
}