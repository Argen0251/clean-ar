package com.example.clean.domain.usecase

import com.example.clean.domain.model.Counter
import com.example.clean.domain.repasitory.CounterRepository

class GetCountUseCase(
    private val repository: CounterRepository
) {
    fun getCount(): Counter {
        return repository.getCount()
    }
}