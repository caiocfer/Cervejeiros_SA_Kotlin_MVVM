package com.ferreiracaio.punkapimvvm.data.response


import com.google.gson.annotations.SerializedName

data class Fermentation(
    @SerializedName("temp")
    val temp: Temp
)