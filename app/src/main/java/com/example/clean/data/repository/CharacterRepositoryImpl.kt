package com.example.clean.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.clean.data.CharacterPagingSource
import com.example.clean.data.datasource.CartoonApiService
import com.example.clean.data.mapper.toDomain
import com.example.clean.data.mapper.toDomainList
import com.example.clean.domain.model.Character
import com.example.clean.domain.repasitory.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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
    override fun getCharactersPaging(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(api) }
        ).flow
            .map { pagingData ->
                pagingData.map { dto -> dto.toDomain() }
            }
    }
}
