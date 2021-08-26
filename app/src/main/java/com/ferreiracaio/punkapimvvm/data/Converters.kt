package com.ferreiracaio.punkapimvvm.data

import androidx.room.TypeConverter
import com.ferreiracaio.punkapimvvm.data.response.Hop
import com.ferreiracaio.punkapimvvm.data.response.Malt
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromHopList(hopList: List<Hop>?):String{
        val type = object: TypeToken<List<Hop>>(){}.type
        return  Gson().toJson(hopList,type)
    }

    @TypeConverter
    fun toHopList(hopListString:String?):List<Hop>?{
        val type = object: TypeToken<List<Hop>>(){}.type
        return Gson().fromJson<List<Hop>>(hopListString, type)
    }

    @TypeConverter
    fun fromMaltList(maltList: List<Malt>?):String{
        val type = object: TypeToken<List<Malt>>(){}.type
        return Gson().toJson(maltList,type)
    }

    @TypeConverter
    fun toMaltList(maltListString:String?):List<Malt>?{
        val type = object: TypeToken<List<Malt>>(){}.type
        return Gson().fromJson<List<Malt>>(maltListString, type)
    }

}