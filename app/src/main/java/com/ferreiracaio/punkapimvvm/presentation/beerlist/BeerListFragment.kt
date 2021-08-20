package com.ferreiracaio.punkapimvvm.presentation.beerlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreiracaio.punkapimvvm.R
import kotlinx.android.synthetic.main.beer_list_adapter.*
import kotlinx.android.synthetic.main.beer_list_fragment.*

class BeerListFragment : Fragment(R.layout.beer_list_fragment) {


    private lateinit var viewModel: BeerListViewModel
    var page:Int=1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeerListViewModel::class.java)
        val recyclerView = recyclerBeerList
        val adapter = BeerListAdapter(viewModel.beerList)
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

//        viewModel.beersLiveData.observe(viewLifecycleOwner, Observer {beerList->
//            beerList?.let {
//                with(recyclerBeerList){
//                    layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
//                    setHasFixedSize(false)
//                    adapter = BeerListAdapter(it)
//                    Log.d("TAG", "Recycler: $adapter")
//
//                }
//            }
//
//        })

        viewModel.beersLiveData.observe(viewLifecycleOwner,{beerList->
            beerList.let {
                adapter.notifyItemRangeInserted(viewModel.beerList.size,25)
            }
        })
        viewModel.getBeers()




        recyclerBeerList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                if(!recyclerView.canScrollVertically(1)){
                if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.beerList.size-1){
                    page += 1
                    viewModel.loadNextPage(page)
                    adapter.notifyItemRangeInserted(viewModel.beerList.size,25)
                }

            }
        })




    }






}