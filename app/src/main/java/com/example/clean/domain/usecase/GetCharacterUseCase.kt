package com.example.clean.domain.usecase

import androidx.lifecycle.LiveData
import com.example.clean.domain.model.Character
import com.example.clean.domain.repasitory.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int): Flow<Character> {
        return repository.getCharacterById(id)
    }
}