package com.ferreiracaio.punkapimvvm.data.repository

import com.ferreiracaio.punkapimvvm.data.db.BeerDAO
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem

class DatabaseDataSource(
    private val beerDAO: BeerDAO
):BeerRepository {
    override suspend fun insertBeer(beerResponseItem: BeerResponseItem) {
        return beerDAO.insertIntoDatabase(beerResponseItem)
    }

    override fun getBeerById(id: Int): BeerResponseItem {
        return beerDAO.getBeerById(id)
    }

    override suspend fun getAllBeersFromDatabase(): List<BeerResponseItem> {
        return beerDAO.getAll()
    }

    override suspend fun deleteBeer(id: Int) {
        return beerDAO.deleteFromDatabase(id)
    }


}