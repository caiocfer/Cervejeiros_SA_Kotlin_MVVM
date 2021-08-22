package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.data.ApiService
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerListViewModel : ViewModel() {

    val beersLiveData: MutableLiveData<List<BeerResponseItem>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int,Int?>> = MutableLiveData()

    val beerList = ArrayList<BeerResponseItem>()
    val isLoading = MutableLiveData<Boolean>()

    fun getBeers() {
        beerList.clear()
        ApiService.service.getBeers().enqueue(object : Callback<List<BeerResponseItem>>{
            override fun onResponse(
                call: Call<List<BeerResponseItem>>,
                response: Response<List<BeerResponseItem>>) {
                when{
                    response.isSuccessful->{
                        response.body()?.let {
                            for(results in response.body()!!){
                                beerList.add(results)
                            }
                        }
                        beersLiveData.value = beerList
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_SUCCESS,null)
                    }else->{
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_message)
                    Log.d("TAG", "onResponse: ${response.code()}")

                }
                }

            }
            override fun onFailure(call: Call<List<BeerResponseItem>>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_message)

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

    fun getBeerSearch(beerName:String){
        beerList.clear()
        isLoading.value = true
        ApiService.service.getBeerByName(beerName).enqueue(object: Callback<List<BeerResponseItem>>{
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
                Log.e("Failure", "onFailure: ${t.stackTrace}", )
            }

        })
    }

    companion object{
        private const val VIEW_FLIPPER_SUCCESS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }




}