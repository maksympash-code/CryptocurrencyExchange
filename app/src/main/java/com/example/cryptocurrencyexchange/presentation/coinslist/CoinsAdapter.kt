package com.example.cryptocurrencyexchange.presentation.coinslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyexchange.R
import com.example.cryptocurrencyexchange.domain.Coin
import com.example.cryptocurrencyexchange.presentation.CoinUi
import com.squareup.picasso.Picasso

class CoinsAdapter(
    private val onItemClick: (CoinUi) -> Unit
): RecyclerView.Adapter<CoinsAdapter.CoinViewHolder>() {

    private val items = mutableListOf<CoinUi>()

    fun submit(newItems: List<CoinUi>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class CoinViewHolder(view: View): RecyclerView.ViewHolder(view){
        val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvSymbol: TextView = view.findViewById(R.id.tvSymbol)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvDayRange: TextView = view.findViewById(R.id.tvDayRange)
        val tvLastUpdate: TextView = view.findViewById(R.id.tvLastUpdate)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CoinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CoinViewHolder,
        position: Int,
    ) {
        val item = items[position]
        holder.tvName.text = item.name
        holder.tvSymbol.text = item.symbol
        holder.tvPrice.text = item.priceText
        holder.tvDayRange.text = item.dayRangeText
        holder.tvLastUpdate.text = item.lastUpdateText

        if (item.imageUrl.isNotEmpty()) {
            Picasso.get()
                .load(item.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivIcon)
        } else {
            holder.ivIcon.setImageDrawable(null)
        }

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size




}