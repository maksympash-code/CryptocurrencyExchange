package com.example.cryptocurrencyexchange.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyexchange.databinding.ActivityMainBinding
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsAdapter
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val coinsAdapter = CoinsAdapter()

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
                lastUpdateText = "Last update: 8:07",
                imageUrl = "https://www.cryptocompare.com/media/37746251/btc.png"
            ) ,
            CoinUi(
                name = "Ethereum",
                symbol = "ETH",
                priceText = "Price: 2315.12 USD",
                dayRangeText = "24h: 2203.00 - 2350.88",
                lastUpdateText = "Last update: 10:12",
                imageUrl = "https://www.cryptocompare.com/media/37746238/eth.png"
            )
        )

        coinsAdapter.submit(dummyList)

    }
}