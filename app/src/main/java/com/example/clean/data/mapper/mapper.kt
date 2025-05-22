package com.example.clean.data.mapper

import com.example.clean.data.model.CounterDto
import com.example.clean.domain.model.Counter
import com.example.clean.domain.model.TypeOfOperation
import kotlin.toString

fun String.toTypeOfOperation(): TypeOfOperation {
    return when (this) {
        "increment" -> TypeOfOperation.INCREMENT
        "decrement" -> TypeOfOperation.DECREMENT
        else -> TypeOfOperation.NONE
    }
}


fun CounterDto.toCounter(): Counter {
    return Counter(
        count = this.count,
        typeOfOperation = this.typeOfOperation.toTypeOfOperation()
    )
}
fun TypeOfOperation.toTypeString(): String =
    when (this) {
        TypeOfOperation.INCREMENT -> "increment"
        TypeOfOperation.DECREMENT -> "decrement"
        TypeOfOperation.NONE      -> "none"
    }

fun Counter.toDto(): CounterDto =
    CounterDto(
        count = count,
        typeOfOperation = typeOfOperation.toTypeString()
    )