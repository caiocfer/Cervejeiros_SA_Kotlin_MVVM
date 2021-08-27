package com.ferreiracaio.punkapimvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem

@Dao
interface BeerDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIntoDatabase(beerResponseItem: BeerResponseItem)

    @Query("SELECT * FROM beer")
    suspend fun getAll(): List<BeerResponseItem>

    @Query("DELETE FROM beer WHERE id = :id")
    suspend fun deleteFromDatabase(id:Int)

    @Query("SELECT * FROM beer WHERE id = :id")
    fun getBeerById(id: Int):BeerResponseItem

}