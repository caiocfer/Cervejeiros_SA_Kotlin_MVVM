package com.ferreiracaio.punkapimvvm.presentation.beerdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import com.ferreiracaio.punkapimvvm.data.response.Malt
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_beer_details.*

class BeerDetailsActivity : AppCompatActivity() {
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
        var malt: String = ""
        var hop:String = ""


        for(malts in beer.ingredients.malt){
            malt += "${malts.name} \n"
            malt += "Amount: ${malts.amount.value} "
            malt += "${malts.amount.unit}\n"
        }

        val hopList = beer.ingredients.hops
        for(hops in hopList){
            hop += "${hops.name}\n"
            hop += "${hops.amount.value} "
            hop += "${hops.amount.unit}\n"
            hop += "Add: ${hops.add}\n"
            hop += "Attribute: ${hops.attribute}\n"

        }

        beerDetailsMalt.text = malt
        beerDetailsHops.text = hop
        beerDetailsYeast.text = beer.ingredients.yeast



    }
}