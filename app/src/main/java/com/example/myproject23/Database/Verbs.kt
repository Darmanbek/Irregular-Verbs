package com.example.myproject23.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "verbs")
data class Verbs(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "qaraqalpaq") val qaraqalpaq: String,
    @ColumnInfo(name = "verb") val verb1: String,
    @ColumnInfo(name = "past_tense") val verb2: String,
    @ColumnInfo(name = "past_participle") val verb3: String
)
