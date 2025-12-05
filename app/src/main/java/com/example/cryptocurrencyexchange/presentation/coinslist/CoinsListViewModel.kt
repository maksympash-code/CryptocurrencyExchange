package com.example.cryptocurrencyexchange.presentation.coinslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptocurrencyexchange.domain.usecases.GetTopCoinsUseCase
import com.example.cryptocurrencyexchange.domain.usecases.RefreshTopCoinsUseCase
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyexchange.domain.Coin
import com.example.cryptocurrencyexchange.presentation.CoinUi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

class CoinsListViewModel(
    private val getTopCoins: GetTopCoinsUseCase,
    private val refreshTopCoins: RefreshTopCoinsUseCase
): ViewModel() {
    private val _uiState = MutableLiveData(CoinsListUiState())
    val uiState: LiveData<CoinsListUiState> = _uiState

    init {
        observeCoins()
        refresh()
    }

    private fun observeCoins(){
        viewModelScope.launch {
            getTopCoins().collectLatest {
                coins -> val uiList = coins.map { it.toUi() }
                _uiState.value = _uiState.value?.copy(coins = uiList) ?: CoinsListUiState(coins = uiList)
            }
        }
    }

    fun refresh() {
        val cur = _uiState.value ?: CoinsListUiState()
        if (cur.isLoading) return

        viewModelScope.launch {
            _uiState.value = cur.copy(isLoading = true)
            // поки без try/catch, як ти просив
            refreshTopCoins()
            _uiState.value = _uiState.value?.copy(isLoading = false)
        }
    }

    // маппер Coin -> CoinUi (мінімальний)
    private fun Coin.toUi(): CoinUi {
        val priceFormatter = DecimalFormat("#,##0.00")
        val priceText = "Price: ${priceFormatter.format(priceUsd)} USD"

        // поки що без реальних min/max 24h – їх покажемо в детальному екрані
        val dayRangeText = "24h: -"

        val lastUpdateText = if (lastUpdate != 0L) {
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            "Last update: ${sdf.format(lastUpdate * 1000)}" // якщо з API секундами
        } else {
            "Last update: -"
        }

        return CoinUi(
            name = name,
            symbol = symbol,
            priceText = priceText,
            dayRangeText = dayRangeText,
            lastUpdateText = lastUpdateText,
            imageUrl = imageUrl
        )
    }
}