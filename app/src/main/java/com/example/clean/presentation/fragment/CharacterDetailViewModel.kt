package com.example.clean.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.domain.model.Character
import com.example.clean.domain.usecase.GetCharacterUseCase

class CharacterDetailViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val id: Int
) : ViewModel() {

    val character: LiveData<Character> = getCharacterUseCase(id)

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
}