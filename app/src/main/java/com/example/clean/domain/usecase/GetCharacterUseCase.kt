package com.example.clean.domain.usecase

import com.example.clean.domain.repasitory.CharacterRepository

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke() = repository.getCharacter()
}
