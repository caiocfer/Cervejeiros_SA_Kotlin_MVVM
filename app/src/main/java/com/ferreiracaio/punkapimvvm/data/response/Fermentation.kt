package com.ferreiracaio.punkapimvvm.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Fermentation(
    @SerializedName("temp")
    val temp: Temp
): Serializable