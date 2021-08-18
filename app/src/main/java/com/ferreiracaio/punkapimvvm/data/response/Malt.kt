package com.ferreiracaio.punkapimvvm.data.response


import com.google.gson.annotations.SerializedName

data class Malt(
    @SerializedName("amount")
    val amount: AmountX,
    @SerializedName("name")
    val name: String
)