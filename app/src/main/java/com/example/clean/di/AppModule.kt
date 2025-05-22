package com.example.clean.di

import com.example.clean.data.datasource.CounterDataSource
import com.example.clean.data.repository.CounterRepositoryImpl
import com.example.clean.domain.repasitory.CounterRepository
import com.example.clean.domain.usecase.DecrementUseCase
import com.example.clean.domain.usecase.GetCountUseCase
import com.example.clean.domain.usecase.IncrementUseCase
import com.example.clean.presentation.activity.TapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module{

    factory { CounterDataSource() }
    single<CounterRepository> {CounterRepositoryImpl(get())}

    factory { IncrementUseCase(get()) }
    factory { DecrementUseCase(get()) }
    factory { GetCountUseCase(get()) }

    viewModel { TapViewModel(
        incrementUseCase = get(),
        decrementUseCase = get(),
        getCountUseCase = get()
    ) }
}