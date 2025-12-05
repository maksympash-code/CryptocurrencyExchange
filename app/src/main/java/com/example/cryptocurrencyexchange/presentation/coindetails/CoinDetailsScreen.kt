package com.example.cryptocurrencyexchange.presentation.coindetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.BoxWithConstraints
import coil.compose.AsyncImage
import com.example.cryptocurrencyexchange.presentation.CoinUi

@Composable
fun CoinDetailsScreen(
    coin: CoinUi?,
    onBack: () -> Unit,
) {
    if (coin == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6EFF7)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    val (minText, maxText) = remember(coin.dayRangeText) {
        val raw = coin.dayRangeText.removePrefix("24h:").trim()
        val parts = raw.split("-")
        val min = parts.getOrNull(0)?.trim().orEmpty()
        val max = parts.getOrNull(1)?.trim().orEmpty()
        min to max
    }

    val priceValue = remember(coin.priceText) {
        coin.priceText.removePrefix("Price:").trim()
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6EFF7))
    ) {
        val isLandscape = maxWidth > maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = coin.symbol,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp
                    )
                )
            }

            val cardModifier = if (isLandscape) {
                Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            } else {
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            }

            Card(
                modifier = cardModifier,
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE6E0EB)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                if (isLandscape) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (coin.imageUrl.isNotEmpty()) {
                            AsyncImage(
                                model = coin.imageUrl,
                                contentDescription = coin.name,
                                modifier = Modifier
                                    .size(96.dp)
                                    .padding(end = 16.dp)
                            )
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = coin.name,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            InfoRows(
                                priceValue = priceValue,
                                minText = minText,
                                maxText = maxText,
                                lastUpdate = coin.lastUpdateText
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (coin.imageUrl.isNotEmpty()) {
                            AsyncImage(
                                model = coin.imageUrl,
                                contentDescription = coin.name,
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(bottom = 16.dp)
                            )
                        }

                        Text(
                            text = coin.name,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        InfoRows(
                            priceValue = priceValue,
                            minText = minText,
                            maxText = maxText,
                            lastUpdate = coin.lastUpdateText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBack,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5E35B1),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .width(160.dp)
                    .height(50.dp)
            ) {
                Text(text = "Back", fontSize = 18.sp)
            }
        }
    }
}

@Composable
private fun InfoRows(
    priceValue: String,
    minText: String,
    maxText: String,
    lastUpdate: String,
) {
    val cleanLastUpdate = lastUpdate.removePrefix("Last update:").trim()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Coin price:", style = MaterialTheme.typography.bodyLarge)
        Text(text = priceValue, style = MaterialTheme.typography.bodyLarge)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "min 24 hours:", style = MaterialTheme.typography.bodyLarge)
        Text(
            text = if (minText.isNotEmpty()) minText else "-",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFFD32F2F))
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "max 24 hours:", style = MaterialTheme.typography.bodyLarge)
        Text(
            text = if (maxText.isNotEmpty()) maxText else "-",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF388E3C))
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Last update:", style = MaterialTheme.typography.bodyLarge)
        Text(text = cleanLastUpdate, style = MaterialTheme.typography.bodyLarge)
    }
}