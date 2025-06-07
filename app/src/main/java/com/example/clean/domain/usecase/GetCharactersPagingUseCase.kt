package com.example.clean.domain.usecase

import androidx.paging.PagingData
import com.example.clean.domain.repasitory.CharacterRepository
import kotlinx.coroutines.flow.Flow
import com.example.clean.domain.model.Character


class GetCharactersPagingUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<PagingData<Character>> =
        repository.getCharactersPaging()
}