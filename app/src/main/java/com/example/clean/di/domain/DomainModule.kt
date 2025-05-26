package com.example.clean.di.domain

import com.example.clean.domain.usecase.GetCharacterUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCharacterUseCase(get()) }
}