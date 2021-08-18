package com.ferreiracaio.punkapimvvm.data

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private val BASE_URL = "https://api.punkapi.com/v2/"

    private fun initRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val service:PunkAPIService = initRetrofit().create(PunkAPIService::class.java)
}