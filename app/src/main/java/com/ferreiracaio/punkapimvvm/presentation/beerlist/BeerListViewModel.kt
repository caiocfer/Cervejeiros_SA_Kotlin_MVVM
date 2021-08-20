package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferreiracaio.punkapimvvm.data.ApiService
import com.ferreiracaio.punkapimvvm.data.response.Beer
import com.ferreiracaio.punkapimvvm.data.response.BeerResponse
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import kotlinx.android.synthetic.main.beer_list_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerListViewModel : ViewModel() {

    val beersLiveData: MutableLiveData<List<BeerResponseItem>> = MutableLiveData()
    val beerList = ArrayList<BeerResponseItem>()
    var currentPage:Int = 1
    var isLoading:Boolean = false

    fun getBeers() {
        ApiService.service.getBeers().enqueue(object : Callback<List<BeerResponseItem>>{
            override fun onResponse(call: Call<List<BeerResponseItem>>, response: Response<List<BeerResponseItem>>) {
                var beers: MutableList<BeerResponseItem> = mutableListOf()
                response.body()?.let {
//                    beers = response.body() as MutableList<BeerResponseItem>
                    for(results in response.body()!!){
                        beerList.add(results)
                    }

                }
                currentPage++
                beersLiveData.value = beerList
            }
            override fun onFailure(call: Call<List<BeerResponseItem>>, t: Throwable) {
            }
        })
    }

    fun loadNextPage(page:Int){
        isLoading
        ApiService.service.loadPage(page).enqueue(object:Callback<List<BeerResponseItem>>{
            override fun onResponse(
                call: Call<List<BeerResponseItem>>,
                response: Response<List<BeerResponseItem>>
            ) {
                var beers: MutableList<BeerResponseItem> = mutableListOf()
                response.body().let {
                    for(results in response.body()!!){
                        beerList.add(results)
                    }
                }
                Log.d("Current page", "onResponse: $page")
                beersLiveData.value = beerList
                !isLoading
                currentPage++

            }

            override fun onFailure(call: Call<List<BeerResponseItem>>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }





}