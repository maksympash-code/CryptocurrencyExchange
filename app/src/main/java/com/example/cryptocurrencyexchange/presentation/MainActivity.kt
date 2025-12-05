package com.example.cryptocurrencyexchange.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyexchange.data.ServiceLocator
import com.example.cryptocurrencyexchange.databinding.ActivityMainBinding
import com.example.cryptocurrencyexchange.presentation.coindetails.CoinDetailsActivity
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsAdapter
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsListUiState
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsListViewModel
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsListViewModelFactory
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val coinsAdapter by lazy {
        CoinsAdapter { coin ->
            val intent = CoinDetailsActivity.newIntent(this, coin)
            startActivity(intent)
        }
    }

    private val serviceLocator by lazy{
        ServiceLocator(applicationContext)
    }

    private val viewModel: CoinsListViewModel by viewModels {
        CoinsListViewModelFactory(
            serviceLocator.getTopCoinsUseCase,
            serviceLocator.refreshTopCoinsUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.rvCoins.apply {
            adapter = coinsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val dummyList = listOf(
            CoinUi(
                name = "Bitcoin",
                symbol = "BTC",
                priceText = "Price: 43547.04 USD",
                dayRangeText = "24h: 43255.67 - 44245.15",
                lastUpdateText = "Last update: 08:07",
                imageUrl = "https://www.cryptocompare.com/media/37746251/btc.png"
            ),
            CoinUi(
                name = "Ethereum",
                symbol = "ETH",
                priceText = "Price: 2315.12 USD",
                dayRangeText = "24h: 2203.00 - 2350.88",
                lastUpdateText = "Last update: 10:12",
                imageUrl = "https://www.cryptocompare.com/media/37746238/eth.png"
            ),
            CoinUi(
                name = "Binance Coin",
                symbol = "BNB",
                priceText = "Price: 270.28 USD",
                dayRangeText = "24h: 255.10 - 280.40",
                lastUpdateText = "Last update: 09:45",
                imageUrl = "https://www.cryptocompare.com/media/40485170/bnb.png"
            ),
            CoinUi(
                name = "Solana",
                symbol = "SOL",
                priceText = "Price: 92.43 USD",
                dayRangeText = "24h: 85.00 - 97.20",
                lastUpdateText = "Last update: 11:03",
                imageUrl = "https://www.cryptocompare.com/media/37747734/sol.png"
            ),
            CoinUi(
                name = "Litecoin",
                symbol = "LTC",
                priceText = "Price: 70.43 USD",
                dayRangeText = "24h: 68.95 - 70.98",
                lastUpdateText = "Last update: 07:56",
                imageUrl = "https://www.cryptocompare.com/media/35309662/ltc.png"
            )
        )

        coinsAdapter.submit(dummyList)

//        viewModel.uiState.observe(this) {
//            state -> render(state)
//        }
    }

    private fun render(state: CoinsListUiState){
        coinsAdapter.submit(state.coins)
    }
}