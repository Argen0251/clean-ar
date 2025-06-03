package com.example.clean.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import com.example.clean.domain.model.Character
import com.example.clean.domain.usecase.GetCharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val id: Int
) : ViewModel() {

    val character: LiveData<Character> = getCharacterUseCase(id)

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
}