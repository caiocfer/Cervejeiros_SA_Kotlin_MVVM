package com.ferreiracaio.punkapimvvm.data.response


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity(tableName = "beer")
data class BeerResponseItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("ingredients")
    @Embedded
    val ingredients: Ingredients,
    @SerializedName("tagline")
    val tagline: String,
):Serializable