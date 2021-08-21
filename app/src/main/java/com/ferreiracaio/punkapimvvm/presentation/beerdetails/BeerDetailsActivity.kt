package com.ferreiracaio.punkapimvvm.presentation.beerdetails

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem

class BeerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_details)

        val beer = intent.extras?.get("BEER_DETAILS") as BeerResponseItem
        Log.d("TAG", "onCreate: ${beer.name}")
    }
}