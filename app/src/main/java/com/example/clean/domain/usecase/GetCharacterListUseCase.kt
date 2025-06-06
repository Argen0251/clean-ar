package com.example.clean.domain.usecase

import androidx.lifecycle.LiveData
import com.example.clean.domain.model.Character
import com.example.clean.domain.repasitory.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterListUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<List<Character>> {
        return repository.getCharactersList()
    }
}