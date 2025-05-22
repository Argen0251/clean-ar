package com.example.clean.domain.repasitory

import com.example.clean.domain.model.Counter

interface CounterRepository {

    fun increment()
    fun decrement()
    fun getCount(): Counter

}