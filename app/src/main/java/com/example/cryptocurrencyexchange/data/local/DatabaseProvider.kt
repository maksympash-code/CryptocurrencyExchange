package com.example.cryptocurrencyexchange.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile
    private var INSTANCE: CryptoDatabase? = null

    fun getDatabase(context: Context): CryptoDatabase {
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CryptoDatabase::class.java,
                "crypto.db",
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}