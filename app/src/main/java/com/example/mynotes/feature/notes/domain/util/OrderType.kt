package com.example.mynotes.featurenote.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
