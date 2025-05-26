package com.example.clean.data.mapper

import com.example.clean.data.model.CharacterDto
import com.example.clean.domain.model.Character


fun CharacterDto.toDomain(): Character =
    Character(
        this.id,
        this.name,
        this.image
    )