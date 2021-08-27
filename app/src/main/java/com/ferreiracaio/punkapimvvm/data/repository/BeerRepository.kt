package com.ferreiracaio.punkapimvvm.data.repository

import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem

interface BeerRepository {
    suspend fun insertBeer(beerResponseItem: BeerResponseItem)
    fun getBeerById(id:Int):BeerResponseItem
    suspend fun getAllBeersFromDatabase():List<BeerResponseItem>
    suspend fun deleteBeer(id:Int)


}