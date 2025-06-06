 package com.example.clean.domain.repasitory

import com.example.clean.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharactersList(): Flow<List<Character>>
    fun getCharacterById(id: Int): Flow<Character>
}
