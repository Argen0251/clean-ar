package com.example.clean.domain.repasitory

import androidx.lifecycle.LiveData
import com.example.clean.domain.model.Character

interface CharacterRepository {
    fun getCharactersList(): LiveData<List<Character>>

    fun getCharacterById(id: Int): LiveData<Character>
}
