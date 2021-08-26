package com.ferreiracaio.punkapimvvm.data.repository

import com.ferreiracaio.punkapimvvm.data.db.BeerDAO
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem

class DatabaseDataSource(
    private val beerDAO: BeerDAO
):BeerRepository {
    override suspend fun insertBeer(beerResponseItem: BeerResponseItem) {
        return beerDAO.insertIntoDatabase(beerResponseItem)
    }

    override suspend fun getAllBeersFromDatabase(): List<BeerResponseItem> {
        return beerDAO.getAll()
    }


}