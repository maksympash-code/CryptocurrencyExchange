package com.example.cryptocurrencyexchange.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptocurrencyexchange.data.local.dao.CoinDao


@Database(
    entities = [CoinEntity::class],
    version = 1,
    exportSchema = false
)

abstract class CryptoDatabase: RoomDatabase() {
    abstract fun coinDao(): CoinDao
}