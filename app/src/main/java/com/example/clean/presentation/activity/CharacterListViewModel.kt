package com.example.clean.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean.domain.usecase.GetCharacterListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.clean.domain.model.Character

class CharacterListViewModel(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : ViewModel() {

    val characters: LiveData<List<Character>> = getCharacterListUseCase()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

}