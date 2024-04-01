package com.example.mynotes.feature.notes.domain.util

import com.example.mynotes.featurenote.domain.util.OrderType

sealed class NoteOrder(val orderType: OrderType){

    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {  //TODO
        return when(this)
        {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }
    }
}