package com.example.clean.data.repository

import com.example.clean.data.datasource.CartoonApiService
import com.example.clean.data.mapper.toDomain
import com.example.clean.data.mapper.toDomainList
import com.example.clean.domain.model.Character
import com.example.clean.domain.repasitory.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

class CharacterRepositoryImpl(
    private val api: CartoonApiService
) : CharacterRepository {

    override fun getCharactersList(): Flow<List<Character>> = flow {
        try {
            val response = api.getCharactersList()
            emit(response.results.toDomainList())
        } catch (e: IOException) {
            throw e
        }
    }.flowOn(Dispatchers.IO)

    override fun getCharacterById(id: Int): Flow<Character> = flow {
        try {
            val dto = api.getCharacter(id)
            emit(dto.toDomain())
        } catch (e: IOException) {
            throw e
        }
    }.flowOn(Dispatchers.IO)
}
