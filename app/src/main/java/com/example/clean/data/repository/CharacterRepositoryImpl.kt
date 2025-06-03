package com.example.clean.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.clean.data.datasource.CartoonApiService
import com.example.clean.data.mapper.toDomain
import com.example.clean.data.mapper.toDomainList
import com.example.clean.domain.model.Character
import com.example.clean.domain.repasitory.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val api: CartoonApiService
) : CharacterRepository {

    override fun getCharactersList(): LiveData<List<Character>> {
        return liveData(Dispatchers.IO) {
            try {
                val response = api.getCharactersList()
                val domainList = response.results.toDomainList()
                emit(domainList)
            } catch (e: Exception) {
                emit(emptyList())
            }
        }
    }

    override fun getCharacterById(id: Int): LiveData<Character> {
        return liveData(Dispatchers.IO) {
            try {
                val dto = api.getCharacter(id)
                val domainItem = dto.toDomain()
                emit(domainItem)
            } catch (e: Exception) {

            }
        }
    }
}