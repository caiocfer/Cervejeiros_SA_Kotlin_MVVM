package com.ferreiracaio.punkapimvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.data.Converters
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import com.ferreiracaio.punkapimvvm.data.response.Hop
import com.ferreiracaio.punkapimvvm.data.response.Malt
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.lang.Exception

@Database(
    entities = [BeerResponseItem::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class BeerDatabase:RoomDatabase() {

    abstract fun  beerDAO():BeerDAO

    companion object{
        @Volatile
        private var INSTANCE: BeerDatabase? = null

        fun getInstace(context: Context):BeerDatabase{
            synchronized(this){
                var instance: BeerDatabase? = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        BeerDatabase::class.java,
                        "beer_database"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }

    }




}