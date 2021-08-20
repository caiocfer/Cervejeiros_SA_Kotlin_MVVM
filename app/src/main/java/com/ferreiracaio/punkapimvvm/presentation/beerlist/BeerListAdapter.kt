package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.data.response.BeerResponse
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.beer_list_adapter.view.*

class BeerListAdapter (
    private val beers: List<BeerResponseItem>,
    val onItemCLickListener:((beer:BeerResponseItem)->Unit)
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
            Picasso.get().load(beerResponseItem.imageUrl).into(itemView.imageBeerList)

            itemView.setOnClickListener {
                onItemCLickListener.invoke(beerResponseItem)
            }
        }

    }
}