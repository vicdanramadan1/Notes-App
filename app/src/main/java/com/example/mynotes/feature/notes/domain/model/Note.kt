package com.example.mynotes.featurenote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynotes.ui.theme.Freesia
import com.example.mynotes.ui.theme.Ivory
import com.example.mynotes.ui.theme.Lint
import com.example.mynotes.ui.theme.RoseWater
import com.example.mynotes.ui.theme.Violet

@Entity
data class Note(
    val title:String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id : Int?, //???why
){
    companion object
    {
        val noteColors = listOf(Freesia , Ivory , Lint , RoseWater , Violet)
    }
}

class InvalidNoteException(message: String): Exception(message)
