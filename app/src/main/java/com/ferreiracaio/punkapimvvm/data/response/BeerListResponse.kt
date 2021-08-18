package com.ferreiracaio.punkapimvvm.data.response

import com.google.gson.annotations.SerializedName

data class BeerListResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
){
    fun getBeerListModel() = Beer(
        name = this.name,
        description = this.description
    )
}

