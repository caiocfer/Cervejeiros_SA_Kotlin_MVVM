package com.ferreiracaio.punkapimvvm.presentation.beerdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ferreiracaio.punkapimvvm.R

class BeerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_details)

        val viewModel:BeerDetailsViewModel = ViewModelProvider(this).get(BeerDetailsViewModel::class.java)

    }
}