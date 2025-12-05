package com.example.cryptocurrencyexchange.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyexchange.data.ServiceLocator
import com.example.cryptocurrencyexchange.databinding.ActivityMainBinding
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsAdapter
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsListUiState
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsListViewModel
import com.example.cryptocurrencyexchange.presentation.coinslist.CoinsListViewModelFactory
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val coinsAdapter = CoinsAdapter()

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

        viewModel.uiState.observe(this) {
            state -> render(state)
        }
    }

    private fun render(state: CoinsListUiState){
        coinsAdapter.submit(state.coins)
    }
}