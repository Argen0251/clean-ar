package com.example.clean.di.domain

import com.example.clean.domain.usecase.GetCharacterListUseCase
import com.example.clean.domain.usecase.GetCharacterUseCase
import com.example.clean.domain.usecase.GetCharactersPagingUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCharacterListUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
    factory { GetCharactersPagingUseCase(get()) }

}