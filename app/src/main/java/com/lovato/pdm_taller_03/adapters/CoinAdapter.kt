package com.lovato.pdm_taller_03.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.lovato.pdm_taller_03.MyCoinAdapter
import com.lovato.pdm_taller_03.R
import com.lovato.pdm_taller_03.models.Coin
import kotlinx.android.synthetic.main.cardview_coin.view.*

class CoinAdapter(var coins: List<Coin>, val clickListener: (Coin) -> Unit): RecyclerView.Adapter<CoinAdapter.ViewHolder>(),
    MyCoinAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_coin, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = coins.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(coins[position], clickListener)

    override fun changeDataSet(newDataSet: List<Coin>) {
        this.coins = newDataSet
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Coin, clickListener: (Coin) -> Unit) = with(itemView){
            Glide.with(itemView.context)
                .load(item.img)
                .placeholder(R.drawable.ic_launcher_background)
                .into(coin_image_cv)

            country_main.text = item.country
            year_main.text = item.year.toString()
            value_us_main.text = item.value_us.toString()
            isAvailable_main.text = item.isAvailable.toString()
            coin_title_cv.text = item.name

            this.setOnClickListener { clickListener(item) }
        }
    }
}