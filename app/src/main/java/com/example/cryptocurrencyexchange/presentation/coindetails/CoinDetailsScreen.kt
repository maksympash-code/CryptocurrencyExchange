package com.example.cryptocurrencyexchange.presentation.coindetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cryptocurrencyexchange.presentation.CoinUi

@Composable
fun CoinDetailsScreen(
    coin: CoinUi,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Основний контент (картка з даними)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Картинка монети
                    AsyncImage(
                        model = coin.imageUrl,
                        contentDescription = "${coin.name} logo",
                        modifier = Modifier
                            .size(96.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Назва по центру
                    Text(
                        text = "${coin.symbol}  ${coin.name}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Ціна
                    Text(
                        text = coin.priceText,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Діапазон за 24 години
                    Text(
                        text = coin.dayRangeText,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Останнє оновлення
                    Text(
                        text = coin.lastUpdateText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Кнопка Back внизу екрана, широка й помітна
        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinDetailsScreenPreview() {
    val sample = CoinUi(
        id = "btc",
        name = "Bitcoin",
        symbol = "BTC",
        priceText = "Price: 43,547.04 USD",
        dayRangeText = "24h: 43,255.67 - 44,245.15",
        lastUpdateText = "Last update: 08:07",
        imageUrl = "https://www.cryptocompare.com/media/37746251/btc.png"
    )
    MaterialTheme {
        CoinDetailsScreen(
            coin = sample,
            onBack = {}
        )
    }
}