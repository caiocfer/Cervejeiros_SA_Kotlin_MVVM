package com.ferreiracaio.punkapimvvm.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Hop(
    @SerializedName("add")
    val add: String,
    @SerializedName("amount")
    val amount: Amount,
    @SerializedName("attribute")
    val attribute: String,
    @SerializedName("name")
    val name: String
):Serializable