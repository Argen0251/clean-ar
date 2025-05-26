package com.example.clean.domain.repasitory

import com.example.clean.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacter(): Character
}
