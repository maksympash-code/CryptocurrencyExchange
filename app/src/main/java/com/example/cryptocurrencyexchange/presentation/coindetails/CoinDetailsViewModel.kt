package com.example.cryptocurrencyexchange.presentation.coindetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyexchange.domain.CoinDetails
import com.example.cryptocurrencyexchange.domain.usecases.GetCoinDetailsUseCase
import com.example.cryptocurrencyexchange.presentation.CoinUi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

class CoinDetailsViewModel(
    private val coinId: String,
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(CoinDetailsUiState())
    val uiState: LiveData<CoinDetailsUiState> = _uiState

    private val priceFormatter = DecimalFormat("#,##0.00")
    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    init {
        observeDetails()
    }

    private fun observeDetails() {
        viewModelScope.launch {
            getCoinDetailsUseCase(coinId).collectLatest { details ->
                val ui = details.toUi()
                _uiState.value = CoinDetailsUiState(
                    isLoading = false,
                    coin = ui
                )
            }
        }
    }

    // маппер CoinDetails -> CoinUi, вже з нормальним 24h діапазоном
    private fun CoinDetails.toUi(): CoinUi {
        val priceText = "Price: ${priceFormatter.format(priceUsd)} USD"
        val dayRangeText = "24h: " +
                "${priceFormatter.format(dayLowUsd)} - ${priceFormatter.format(dayHighUsd)}"
        val lastUpdateText = "Last update: ${timeFormatter.format(lastUpdate * 1000)}"

        return CoinUi(
            id = id,
            name = name,
            symbol = symbol,
            priceText = priceText,
            dayRangeText = dayRangeText,
            lastUpdateText = lastUpdateText,
            imageUrl = imageUrl
        )
    }
}