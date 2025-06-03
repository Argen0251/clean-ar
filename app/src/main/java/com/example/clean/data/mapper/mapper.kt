package com.example.clean.data.mapper

import com.example.clean.data.model.CharacterResponse
import com.example.clean.domain.model.Character


fun CharacterResponse.Result.toDomain(): Character {
    return Character(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        image = this.image.orEmpty()
    )
}

fun List<CharacterResponse.Result>.toDomainList(): List<Character> {
    return map { it.toDomain() }
}