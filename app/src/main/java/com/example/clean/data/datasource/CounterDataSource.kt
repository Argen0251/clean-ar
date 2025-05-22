    package com.example.clean.data.datasource

    import com.example.clean.data.model.CounterDto

    object CounterDataSource {

        private var count =0
        private var typeOfOperation ="none"

        fun increment(){
            count++
            typeOfOperation="increment"
        }
        fun decrement(){
            count--
            typeOfOperation="decrement"
        }
        fun getCount() : CounterDto{
            return CounterDto(
                count = count,
                typeOfOperation = typeOfOperation
            )
        }
    }