package com.example.cryptocurrencyexchange.presentation.coindetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cryptocurrencyexchange.R
import com.example.cryptocurrencyexchange.presentation.CoinUi

class CoinDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra(EXTRA_NAME) ?: ""
        val symbol = intent.getStringExtra(EXTRA_SYMBOL) ?: ""
        val priceText = intent.getStringExtra(EXTRA_PRICE) ?: ""
        val dayRangeText = intent.getStringExtra(EXTRA_DAY_RANGE) ?: ""
        val lastUpdateText = intent.getStringExtra(EXTRA_LAST_UPDATE) ?: ""
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL) ?: ""

        val uiModel = CoinUi (
            name = name,
            symbol = symbol,
            priceText = priceText,
            dayRangeText = dayRangeText,
            lastUpdateText = lastUpdateText,
            imageUrl = imageUrl,
        )

        setContent {
            MaterialTheme {
                Surface {
                    CoinDetailsScreen(coin = uiModel, onBack = { finish() })
                }
            }
        }
    }

    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_SYMBOL = "extra_symbol"
        private const val EXTRA_PRICE = "extra_price"
        private const val EXTRA_DAY_RANGE = "extra_day_range"
        private const val EXTRA_LAST_UPDATE = "extra_last_update"
        private const val EXTRA_IMAGE_URL = "extra_image_url"

        fun newIntent(context: Context, coin: CoinUi): Intent =
            Intent(context, CoinDetailsActivity::class.java).apply {
                putExtra(EXTRA_NAME, coin.name)
                putExtra(EXTRA_SYMBOL, coin.symbol)
                putExtra(EXTRA_PRICE, coin.priceText)
                putExtra(EXTRA_DAY_RANGE, coin.dayRangeText)
                putExtra(EXTRA_LAST_UPDATE, coin.lastUpdateText)
                putExtra(EXTRA_IMAGE_URL, coin.imageUrl)
            }
    }
}