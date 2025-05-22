package com.example.clean.domain.usecase

import com.example.clean.domain.repasitory.CounterRepository

class IncrementUseCase(
    private val repository: CounterRepository
) {
    fun increment() {
        repository.increment()
    }
}