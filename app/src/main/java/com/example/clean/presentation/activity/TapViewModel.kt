package com.example.clean.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.domain.model.Counter
import com.example.clean.domain.usecase.DecrementUseCase
import com.example.clean.domain.usecase.GetCountUseCase
import com.example.clean.domain.usecase.IncrementUseCase

class TapViewModel(
    private val incrementUseCase: IncrementUseCase,
    private val decrementUseCase: DecrementUseCase,
    private val getCountUseCase: GetCountUseCase
    ): ViewModel() {

    private val _counter = MutableLiveData<Counter>()
    val counter: LiveData<Counter> = _counter

        fun increment(){
            incrementUseCase.increment()
            _counter.value= getCountUseCase.getCount()
        }
        fun decrement(){
            decrementUseCase.decrement()
            _counter.value= getCountUseCase.getCount()
        }

}