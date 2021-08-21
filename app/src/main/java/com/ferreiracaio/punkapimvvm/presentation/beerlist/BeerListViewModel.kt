package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferreiracaio.punkapimvvm.data.ApiService
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerListViewModel : ViewModel() {

    val beersLiveData: MutableLiveData<List<BeerResponseItem>> = MutableLiveData()
    val beerList = ArrayList<BeerResponseItem>()
    val isLoading = MutableLiveData<Boolean>()

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
        isLoading.value = true
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
                isLoading.value = false
            }

            override fun onFailure(call: Call<List<BeerResponseItem>>, t: Throwable) {
                Log.e("Failure", "${t.stackTrace} ", )
            }

        })
    }

    fun getIsLoading(): LiveData<Boolean> {
        return getIsLoading()
    }

}