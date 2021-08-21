package com.ferreiracaio.punkapimvvm.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Method(
    @SerializedName("fermentation")
    val fermentation: Fermentation,
    @SerializedName("mash_temp")
    val mashTemp: List<MashTemp>,
    @SerializedName("twist")
    val twist: String
): Serializable