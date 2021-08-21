package com.ferreiracaio.punkapimvvm.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ingredients(
    @SerializedName("hops")
    val hops: List<Hop>,
    @SerializedName("malt")
    val malt: List<Malt>,
    @SerializedName("yeast")
    val yeast: String
):Serializable