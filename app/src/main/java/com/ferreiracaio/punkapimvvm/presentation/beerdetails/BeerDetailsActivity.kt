package com.ferreiracaio.punkapimvvm.presentation.beerdetails

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.data.db.BeerDAO
import com.ferreiracaio.punkapimvvm.data.db.BeerDatabase
import com.ferreiracaio.punkapimvvm.data.repository.BeerRepository
import com.ferreiracaio.punkapimvvm.data.repository.DatabaseDataSource
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_beer_details.*

class BeerDetailsActivity : AppCompatActivity() {
    private val viewModel:BeerDetailsViewModel by viewModels {
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val beerDAO: BeerDAO =
                    BeerDatabase.getInstace(this@BeerDetailsActivity).beerDAO()
                val repository: BeerRepository = DatabaseDataSource(beerDAO)
                return BeerDetailsViewModel(repository) as T
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_details)

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

        button.setOnClickListener {
            viewModel.insertBeer(beer)
        }




    }
}