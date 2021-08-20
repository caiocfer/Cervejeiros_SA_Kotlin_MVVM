package com.ferreiracaio.punkapimvvm.data

import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkAPIService {
    @GET("beers")
    fun getBeers():Call<List<BeerResponseItem>>

    @GET("beers")
    fun loadPage(
        @Query("page") page:Int
    ):Call<List<BeerResponseItem>>
}