package com.lovato.pdm_taller_03

import com.lovato.pdm_taller_03.models.Coin

object AppConstants{

    val dataset_saveinstance_key = "CLAVIER"
    val MAIN_LIST_KEY = "key_lis_coin"
}

interface MyCoinAdapter{
    fun changeDataSet(newDataSet: List<Coin>)
}