package com.example.clean.domain.model

data class Counter(
    val count: Int,
    val typeOfOperation: TypeOfOperation = TypeOfOperation.NONE
)
enum class TypeOfOperation {
    INCREMENT,
    DECREMENT,
    NONE
}