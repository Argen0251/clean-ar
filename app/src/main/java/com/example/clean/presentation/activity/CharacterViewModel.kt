package com.example.clean.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean.domain.model.Character
import com.example.clean.domain.usecase.GetCharacterUseCase
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    fun loadData() {
        viewModelScope.launch {
            _character.value = getCharacterUseCase()
        }
    }
}
