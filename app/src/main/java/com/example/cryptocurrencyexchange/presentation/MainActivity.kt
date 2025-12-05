package com.example.cryptocurrencyexchange.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyexchange.data.ServiceLocator
import com.example.cryptocurrencyexchange.databinding.ActivityMainBinding
import com.example.cryptocurrencyexchange.presentation.coindetails.CoinDetailsActivity
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsAdapter
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsListUiState
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsListViewModel
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsListViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val serviceLocator by lazy {
        ServiceLocator(applicationContext)
    }
    private val coinsAdapter by lazy {
        CoinsAdapter { coin ->
            val intent = CoinDetailsActivity.newIntent(this, coin.id)
            startActivity(intent)
        }
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

        viewModel.uiState.observe(this) { state ->
            render(state)
        }
    }

    private fun render(state: CoinsListUiState) {
        coinsAdapter.submit(state.coins)

        if (state.errorMessage != null) {
            Toast.makeText(this, state.errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.onErrorShown()
        }
    }
}