package com.example.myproject23.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Verbs::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): VerbsDao

    companion object{
        private lateinit var INSTANCE: AppDatabase

        fun getInstance(context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "verbs_v6.db"
            )
                .createFromAsset("verbs_v6.db")
                .allowMainThreadQueries()
                .addMigrations()
                .build()
        }
}