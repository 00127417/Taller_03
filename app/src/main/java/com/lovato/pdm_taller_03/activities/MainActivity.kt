package com.lovato.pdm_taller_03.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.lovato.pdm_taller_03.AppConstants
import com.lovato.pdm_taller_03.R
import com.lovato.pdm_taller_03.fragments.MainContentFragment
import com.lovato.pdm_taller_03.fragments.MainListFragment
import com.lovato.pdm_taller_03.models.Coin
import com.lovato.pdm_taller_03.network.NetworkUtils
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity(), MainListFragment.SearchNewCoinListener {
    private lateinit var mainFragment : MainListFragment
    private lateinit var mainContentFragment: MainContentFragment

    private var coinList = ArrayList<Coin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coinList = savedInstanceState?.getParcelableArrayList(AppConstants.dataset_saveinstance_key) ?: ArrayList()

        initMainFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.dataset_saveinstance_key, coinList)
        super.onSaveInstanceState(outState)
    }

    fun initMainFragment(){
        mainFragment = MainListFragment.newInstance(coinList)

        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.main_fragment
        else {
            mainContentFragment = MainContentFragment.newInstance(Coin())
            changeFragment(R.id.land_main_cont_fragment, mainContentFragment)

            R.id.land_main_fragment
        }

        changeFragment(resource, mainFragment)
    }

    fun addCoinToList(coin: Coin) {
        coinList.add(coin)
        mainFragment.updateCoinsAdapter(coinList)
        Log.d("Number", coinList.size.toString())
    }

    override fun searchCoin(coinName: String) {
        FetchCoin().execute(coinName)
    }

    override fun managePortraitItemClick(coin: Coin) {
        val coinBundle = Bundle()
        coinBundle.putParcelable("COIN", coin)
        startActivity(Intent(this, CoinViewerActivity::class.java).putExtras(coinBundle))
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    override fun manageLandscapeItemClick(coin: Coin) {
        mainContentFragment = MainContentFragment.newInstance(coin)
        changeFragment(R.id.land_main_cont_fragment, mainContentFragment)
    }

    private inner class FetchCoin : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String): String {

            if (params.isNullOrEmpty()) return ""

            val coinName = params[0]
            val coinUrl = NetworkUtils().buildtSearchUrl(coinName)

            return try {
                NetworkUtils().getResponseFromHttpUrl(coinUrl)
            } catch (e: IOException) {
                ""
            }
        }

        override fun onPostExecute(coinInfo: String) {
            super.onPostExecute(coinInfo)
            if (!coinInfo.isEmpty()) {
                val coinJson = JSONObject(coinInfo)
                if (coinJson.getString("Response") == "True") {
                    val coin = Gson().fromJson<Coin>(coinInfo, Coin::class.java)
                    addCoinToList(coin)
                } else {
                    Toast.makeText(this@MainActivity, "No existe en la base de datos,", Toast.LENGTH_LONG).show()
                }
            }else
            {
                Toast.makeText(this@MainActivity, "A ocurrido un error,", Toast.LENGTH_LONG).show()
            }
        }
    }
}


