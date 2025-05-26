package com.example.clean.di.presentation

import com.example.clean.presentation.activity.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<CharacterViewModel> {
        CharacterViewModel(
            getCharacterUseCase = get()
        )
    }
}