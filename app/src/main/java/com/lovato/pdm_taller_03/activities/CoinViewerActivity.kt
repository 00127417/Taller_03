package com.lovato.pdm_taller_03.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.lovato.pdm_taller_03.R
import com.lovato.pdm_taller_03.models.Coin
import kotlinx.android.synthetic.main.viewer_coin.*


class CoinViewerActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewer_coin)

        setSupportActionBar(toolbarviewer)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(true)
        collapsingtoolbarviewer.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingtoolbarviewer.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        val reciever: Coin = intent?.extras?.getParcelable("MOVIE") ?: Coin()
        init(reciever)
    }

    fun init(coin: Coin){
        Glide.with(this)
            .load(coin.img)
            .placeholder(R.drawable.ic_launcher_background)
            .into(app_bar_image_viewer)
        collapsingtoolbarviewer.title = coin.name
        country_viewer.text = coin.country
        value_us_viewer.text = coin.value_us.toString()
        isAvailable_viewer.text = coin.isAvailable.toString()
        year_viewer.text = coin.year.toString()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {onBackPressed();true}
            else -> super.onOptionsItemSelected(item)
        }
    }
}