package com.ferreiracaio.punkapimvvm.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MashTemp(
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("temp")
    val temp: TempX
): Serializable