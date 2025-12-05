package com.example.cryptocurrencyexchange.presentation.coindetails

import android.widget.ImageView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.room.util.TableInfo
import com.example.cryptocurrencyexchange.presentation.CoinUi
import com.squareup.picasso.Picasso

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailsScreen(
    coin: CoinUi,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title =  { Text(text = coin.symbol) },
                navigationIcon = {
                    Button(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            CoinImage(imageUrl = coin.imageUrl)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Name ${coin.name}")
            Text(text = coin.priceText)
            Text(text = coin.dayRangeText)
            Text(text = coin.lastUpdateText)
        }
    }
}

@Composable
private fun CoinImage(imageUrl: String){
    val context = LocalContext.current

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        factory = {
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        update = { imageView ->
            if (imageUrl.isNotEmpty()) {
                Picasso.get()
                    .load(imageUrl)
                    .into(imageView)
            } else {
                imageView.setImageDrawable(null)
            }
        }
    )
}