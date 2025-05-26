package com.example.clean.data.repository

import com.example.clean.data.datasource.CartoonApiService
import com.example.clean.data.mapper.toDomain
import com.example.clean.domain.model.Character
import com.example.clean.domain.repasitory.CharacterRepository

class CharacterRepositoryImpl(
    private val api: CartoonApiService
) : CharacterRepository {
    override suspend fun getCharacter(): Character =
        api.getCharacters().toDomain()
}
