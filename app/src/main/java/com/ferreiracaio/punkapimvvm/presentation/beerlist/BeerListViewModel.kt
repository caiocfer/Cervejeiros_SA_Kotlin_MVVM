package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreiracaio.punkapimvvm.data.ApiService
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerListViewModel : ViewModel() {

    val beersLiveData: MutableLiveData<List<BeerResponseItem>> = MutableLiveData()
    val beerList = ArrayList<BeerResponseItem>()

    fun getBeers() {
        ApiService.service.getBeers().enqueue(object : Callback<List<BeerResponseItem>>{
            override fun onResponse(call: Call<List<BeerResponseItem>>, response: Response<List<BeerResponseItem>>) {
                response.body()?.let {
                    for(results in response.body()!!){
                        beerList.add(results)
                    }
                }
                beersLiveData.value = beerList
            }
            override fun onFailure(call: Call<List<BeerResponseItem>>, t: Throwable) {
            }
        })
    }

    fun loadNextPage(page:Int){
        ApiService.service.loadPage(page).enqueue(object:Callback<List<BeerResponseItem>>{
            override fun onResponse(
                call: Call<List<BeerResponseItem>>,
                response: Response<List<BeerResponseItem>>
            ) {
                response.body().let {
                    for(results in response.body()!!){
                        beerList.add(results)
                    }
                }
                beersLiveData.value = beerList
            }

            override fun onFailure(call: Call<List<BeerResponseItem>>, t: Throwable) {
                Log.e("Failure", "${t.stackTrace} ", )
            }

        })
    }

}