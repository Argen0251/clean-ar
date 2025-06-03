package com.example.clean.domain.usecase

import androidx.lifecycle.LiveData
import com.example.clean.domain.model.Character
import com.example.clean.domain.repasitory.CharacterRepository

class GetCharacterListUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(): LiveData<List<Character>> {
        return repository.getCharactersList()
    }
}