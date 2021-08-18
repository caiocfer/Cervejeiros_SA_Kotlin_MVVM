package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferreiracaio.punkapimvvm.data.ApiService
import com.ferreiracaio.punkapimvvm.data.response.Beer
import com.ferreiracaio.punkapimvvm.data.response.BeerResponse
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerListViewModel : ViewModel() {
    val beersLiveData: MutableLiveData<List<BeerResponseItem>> = MutableLiveData()

    fun getBeers() {
        ApiService.service.getBeers().enqueue(object : Callback<List<BeerResponseItem>>{
            override fun onResponse(call: Call<List<BeerResponseItem>>, response: Response<List<BeerResponseItem>>) {
                var beers: MutableList<BeerResponseItem> = mutableListOf()
                response.body()?.let {
                    beers = response.body() as MutableList<BeerResponseItem>
                    Log.d("TAG", "onResponse: ${beers[10].name}")
                }
                beersLiveData.value = beers
            }

            override fun onFailure(call: Call<List<BeerResponseItem>>, t: Throwable) {
            }

        })


    }
}