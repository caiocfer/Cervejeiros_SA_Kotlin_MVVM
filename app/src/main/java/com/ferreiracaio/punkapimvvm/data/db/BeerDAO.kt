package com.ferreiracaio.punkapimvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem

@Dao
interface BeerDAO {

    @Insert
    fun insertIntoDatabase(beerResponseItem: BeerResponseItem)

    @Query("SELECT * FROM beer")
    fun getAll(): List<BeerResponseItem>

}