package com.example.cryptocurrencyexchange.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocurrencyexchange.data.local.CoinEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface CoinDao {

    @Query("SELECT * FROM coins ORDER BY symbol")
    fun getCoins(): Flow<List<CoinEntity>>

    @Query("SELECT * FROM coins WHERE symbol = :symbol LIMIT 1")
    fun getCoin(symbol: String): Flow<CoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(coins: List<CoinEntity>)

    @Query("DELETE FROM coins")
    suspend fun clearAll()
}