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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PetViewModel: ViewModel() {
    private val petApiService = RetrofitHelper.getInstance().create(PetServiceApi::class.java)
    private val repository = PetRepo(petApiService)

    var pets by mutableStateOf(listOf<Pet>())
    var addSuccess by mutableStateOf(false)

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

    fun addPet(pet: Pet){
        viewModelScope.launch {

            try {
                val response = petApiService.addPet(pet)
                if(response.isSuccessful && response.body() != null){
                    addSuccess = true
                } else {
                    addSuccess = false
                }

            } catch (e: Exception){
                Log.i("ADD_PET_KEY", e.message.toString())
            }
        }
    }

    fun deletePet(id: Int){
        viewModelScope.launch {
            try {
                val response = petApiService.deletePet(id)
                if(response.isSuccessful){
                    //pet has been deleted
                } else {
                    //failed to delete
                }
            } catch (e: Exception){
                Log.i("DELETE_PET_KEY", e.message.toString())
            }
        }
    }

    fun closeDialog(milli: Long, onDismiss: () -> Unit){
        viewModelScope.launch {
            delay(milli)
            addSuccess = false
            onDismiss()
        }
    }
}