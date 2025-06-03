package com.example.clean.di.presentation

import com.example.clean.presentation.activity.CharacterDetailViewModel
import com.example.clean.presentation.activity.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        CharacterListViewModel(
            getCharacterListUseCase = get()
        )
    }

    viewModel { params ->
        CharacterDetailViewModel(
            getCharacterUseCase = get(),
            id = params.get()
        )
    }
}