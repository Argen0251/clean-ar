package com.example.clean.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.clean.domain.model.Character
import com.example.clean.domain.usecase.GetCharacterListUseCase
import com.example.clean.domain.usecase.GetCharactersPagingUseCase
import com.example.clean.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharactersPagingUseCase: GetCharactersPagingUseCase
) : ViewModel() {

    private val _charactersState = MutableStateFlow<UIState<List<Character>>>(UIState.Loading())
    val charactersState: StateFlow<UIState<List<Character>>> = _charactersState

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _charactersState.value = UIState.Loading()

            getCharacterListUseCase()
                .catch { error ->
                    _charactersState.value = UIState.Error(error.localizedMessage ?: "Неизвестная ошибка")
                }
                .collect { list ->
                    if (list.isEmpty()) {
                        _charactersState.value = UIState.Empty()
                    } else {
                        _charactersState.value = UIState.Success(list)
                    }
                }
        }
    }
    val charactersPagingFlow: Flow<PagingData<Character>> =
        getCharactersPagingUseCase()
            .cachedIn(viewModelScope)
}
