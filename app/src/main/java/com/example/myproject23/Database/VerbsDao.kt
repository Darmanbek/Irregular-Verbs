package com.example.myproject23.Database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VerbsDao {

    @Query("SELECT * FROM verbs")
    fun getAll(): List<Verbs>

    @Query("SELECT * FROM verbs WHERE qaraqalpaq like (:name) OR verb like (:name) OR past_tense like (:name) OR past_participle like (:name)")
    fun getData(name: String): List<Verbs>

}