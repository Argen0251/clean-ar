package com.example.clean.data.datasource

import com.example.clean.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    suspend fun getCharactersList(): CharacterResponse
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int):
            CharacterResponse.Result
}