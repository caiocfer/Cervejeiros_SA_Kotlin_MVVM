package com.ferreiracaio.punkapimvvm.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AmountX(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
): Serializable