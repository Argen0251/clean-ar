package com.example.clean.data.datasource

import com.example.clean.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CartoonApiService {
    @GET("character")
    suspend fun getCharactersList(
        @Query("page") page: Int = 1
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterResponse.Result
}