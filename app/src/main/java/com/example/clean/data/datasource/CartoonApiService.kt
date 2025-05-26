package com.example.clean.data.datasource

import com.example.clean.data.model.CharacterDto
import retrofit2.http.GET

interface CartoonApiService {
    @GET("character/1")
    suspend fun getCharacters() : CharacterDto

}