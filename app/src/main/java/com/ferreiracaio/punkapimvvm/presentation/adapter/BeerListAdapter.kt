package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.data.response.BeerResponse
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import com.ferreiracaio.punkapimvvm.presentation.beerdetails.BeerDetailsActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.beer_list_adapter.view.*
import java.lang.Exception

class BeerListAdapter (
    private val beers: List<BeerResponseItem>
):RecyclerView.Adapter<BeerListAdapter.BeerListViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.beer_list_adapter,parent,false)
        return BeerListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        holder.bindView(beers[position])


    }

    override fun getItemCount() = beers.size

    inner class BeerListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        private val textViewBeerListName: TextView = itemView.textBeerListName
        private val textViewBeerListDescription: TextView = itemView.textBeerListDescription
        private val imageViewBeerList: ImageView = itemView.imageBeerList

        fun bindView(beerResponseItem: BeerResponseItem){
            textViewBeerListName.text = beerResponseItem.name
            textViewBeerListDescription.text = beerResponseItem.tagline
            Picasso.get()
                .load(beerResponseItem.imageUrl)
                .noFade()
                .into(itemView.imageBeerList,object : Callback{
                    override fun onSuccess() {
                        itemView.imageProgress.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        Log.e("Error", "onError: ${e?.message}", )
                    }

                })


            itemView.setOnClickListener {
                val activity = itemView.context as Activity
                val intent:Intent = Intent(activity,BeerDetailsActivity::class.java)
                intent.putExtra("BEER_DETAILS", beers[position])
                activity.startActivity(intent)
            }



        }

    }
}