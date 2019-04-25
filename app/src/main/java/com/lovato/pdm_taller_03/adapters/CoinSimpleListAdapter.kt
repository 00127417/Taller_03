package com.lovato.pdm_taller_03.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lovato.pdm_taller_03.MyCoinAdapter
import com.lovato.pdm_taller_03.R
import com.lovato.pdm_taller_03.models.Coin
import kotlinx.android.synthetic.main.cardview_coin.view.*


class CoinSimpleListAdapter(var coins:List<Coin>, val clickListener: (Coin) -> Unit): RecyclerView.Adapter<CoinSimpleListAdapter.ViewHolder>(),
    MyCoinAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_coin, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = coins.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) =holder.bind(coins[pos], clickListener)

    override fun changeDataSet(newDataSet: List<Coin>) {
        this.coins = newDataSet
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(coin: Coin, clickListener: (Coin) -> Unit) = with(itemView){
            country_main.text = coin.country
            year_main.text = coin.year.toString()
            value_us_main.text = coin.value_us.toString()
            isAvailable_main.text = coin.isAvailable.toString()
            coin_title_cv.text = coin.name
            this.setOnClickListener { clickListener(coin) }
        }
    }
}