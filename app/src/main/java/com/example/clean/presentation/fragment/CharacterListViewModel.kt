package com.example.clean.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.domain.model.Character
import com.example.clean.domain.usecase.GetCharacterListUseCase

class CharacterListViewModel(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : ViewModel() {

    val characters: LiveData<List<Character>> = getCharacterListUseCase()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

}