package com.example.petsstore.repositories

import com.example.petsstore.interfaces.PetServiceApi

class PetRepo(private val api: PetServiceApi) {

    suspend fun getAllPets() = api.getAllPets()
}