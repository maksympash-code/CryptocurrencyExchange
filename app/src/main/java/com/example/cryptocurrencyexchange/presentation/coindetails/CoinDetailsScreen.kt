package com.example.cryptocurrencyexchange.presentation.coindetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cryptocurrencyexchange.presentation.CoinUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailsScreen(
    coin: CoinUi,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = coin.symbol,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = coin.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(coin.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = coin.name,
                        modifier = Modifier.size(160.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = coin.name,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InfoRow("Price:", coin.priceText)
                    InfoRow("24h range:", coin.dayRangeText)
                    InfoRow("Last update:", coin.lastUpdateText)
                }
            }

            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp, bottom = 8.dp)
            ) {
                Text(text = "Back")
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 14.sp)
        Text(text = value, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinDetailsScreenPreview() {
    val dummy = CoinUi(
        name = "Bitcoin",
        symbol = "BTC",
        priceText = "Price: 43547.04 USD",
        dayRangeText = "24h: 43255.67 - 44245.15",
        lastUpdateText = "Last update: 08:07",
        imageUrl = "https://www.cryptocompare.com/media/37746251/btc.png"
    )
    MaterialTheme {
        CoinDetailsScreen(coin = dummy, onBackClick = {})
    }
}