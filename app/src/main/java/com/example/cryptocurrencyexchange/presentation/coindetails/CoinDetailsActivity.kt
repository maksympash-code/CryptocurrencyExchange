package com.example.cryptocurrencyexchange.presentation.coindetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.cryptocurrencyexchange.data.ServiceLocator
import com.example.cryptocurrencyexchange.presentation.CoinUi

class CoinDetailsActivity : ComponentActivity() {

    private val serviceLocator by lazy {
        ServiceLocator(applicationContext)
    }

    private val viewModel: CoinDetailsViewModel by viewModels {
        val id = intent.getStringExtra(EXTRA_COIN_ID) ?: ""
        CoinDetailsViewModelFactory(
            coinId = id,
            getCoinDetailsUseCase = serviceLocator.getCoinDetailsUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val state by viewModel.uiState.observeAsState(CoinDetailsUiState())
                val coin: CoinUi? = state.coin

                if (coin != null) {
                    CoinDetailsScreen(
                        coin = coin,
                        onBack = { finish() }
                    )
                }
            }
        }
    }

    companion object {
        private const val EXTRA_COIN_ID = "extra_coin_id"

        fun newIntent(context: Context, id: String): Intent =
            Intent(context, CoinDetailsActivity::class.java).apply {
                putExtra(EXTRA_COIN_ID, id)
            }
    }
}