package com.ferreiracaio.punkapimvvm.presentation.beerdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_beer_details.*

class BeerDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel:BeerDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_details)

        viewModel = ViewModelProvider(this).get(BeerDetailsViewModel::class.java)
        val beer = intent.extras?.get("BEER_DETAILS") as BeerResponseItem

        setSupportActionBar(beerDetailsToolbar).apply {
            title = beer.name
            titleColor = R.color.white
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        Picasso.get().load(beer.imageUrl).into(imageBeerDetails)

        beerDetailsDescription.text = beer.description
        beerDetailsMalt.text = viewModel.getMalt(beer)
        beerDetailsHops.text = viewModel.getHops(beer)
        beerDetailsYeast.text = viewModel.getYeast(beer)



    }
}