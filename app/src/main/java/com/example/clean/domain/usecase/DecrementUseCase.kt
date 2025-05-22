package com.example.clean.domain.usecase

import com.example.clean.domain.repasitory.CounterRepository

class DecrementUseCase(
    private val repository: CounterRepository
) {
    fun decrement() {
        repository.decrement()
    }
}