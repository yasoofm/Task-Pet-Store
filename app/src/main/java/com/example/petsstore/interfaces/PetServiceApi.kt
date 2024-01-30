package com.example.petsstore.interfaces

import com.example.petsstore.models.Pet
import retrofit2.http.GET

interface PetServiceApi {

    @GET("pets")
    suspend fun getAllPets(): List<Pet>
}