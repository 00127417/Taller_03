package com.lovato.pdm_taller_03.network

import android.net.Uri
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class NetworkUtils {

    val COINS_API_BASEURL = "https://basecoins-14b7b.firebaseio.com/coin"

    fun buildtSearchUrl(coinName: String) : URL {
        val builtUri = Uri.parse(COINS_API_BASEURL)
            .buildUpon()
            .appendPath(coinName+".json")
            .build()

        println("----------------------")
        println(builtUri.toString())
        println("---------------------------")
        return try {
            URL(builtUri.toString())
        }catch (e : MalformedURLException){
            URL("")
        }
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL):String{
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.inputStream

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if(hasInput){
                scanner.next()
            }else{
                ""
            }
        }finally {
            urlConnection.disconnect()
        }
    }

}