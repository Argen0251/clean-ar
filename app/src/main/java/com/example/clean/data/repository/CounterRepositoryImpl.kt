package com.example.clean.data.repository

import com.example.clean.data.datasource.CounterDataSource
import com.example.clean.data.mapper.toCounter
import com.example.clean.data.mapper.toTypeOfOperation
import com.example.clean.domain.model.Counter
import com.example.clean.domain.repasitory.CounterRepository

class CounterRepositoryImpl(
    private val api :CounterDataSource) : CounterRepository {


    override fun increment() {
        api.increment()
    }

    override fun decrement() {
        api.decrement()
    }

    override fun getCount(): Counter {
        return api.getCount().toCounter()
    }
}