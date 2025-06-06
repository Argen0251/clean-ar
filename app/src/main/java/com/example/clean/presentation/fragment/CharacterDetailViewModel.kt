package com.example.clean.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean.domain.model.Character
import com.example.clean.domain.usecase.GetCharacterUseCase
import com.example.clean.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val id: Int
) : ViewModel() {

    private val _characterState = MutableStateFlow<UIState<Character>>(UIState.Empty())
    val characterState: StateFlow<UIState<Character>> = _characterState

    init {
        getCharacter()
    }

    private fun getCharacter() {
        viewModelScope.launch {
            _characterState.value = UIState.Loading()
            getCharacterUseCase(id)
                .catch { error ->
                    _characterState.value = UIState.Error(error.localizedMessage ?: "Неизвестная ошибка")
                }
                .collect { character ->
                    _characterState.value = UIState.Success(character)
                }
        }
    }
}
