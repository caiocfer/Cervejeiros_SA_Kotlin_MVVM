package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreiracaio.punkapimvvm.R
import kotlinx.android.synthetic.main.beer_list_fragment.*

class BeerListFragment : Fragment(R.layout.beer_list_fragment) {

    private lateinit var viewModel: BeerListViewModel
    var page:Int=1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeerListViewModel::class.java)
        val adapter = BeerListAdapter(viewModel.beerList)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        recyclerBeerList.layoutManager = linearLayoutManager
        recyclerBeerList.setHasFixedSize(true)
        recyclerBeerList.adapter = adapter

        viewModel.beersLiveData.observe(viewLifecycleOwner,{beerList->
            beerList.let {
                adapter.notifyItemRangeInserted(viewModel.beerList.size,25)
            }
        })
        viewModel.getBeers()

        recyclerBeerList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy>0){
                    val visibleItemCount = linearLayoutManager.childCount;
                    val totalItemCount = linearLayoutManager.itemCount;
                    val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if((visibleItemCount+pastVisibleItems) >= totalItemCount && page<=13){
                        recyclerBeerList.post {
                            page += 1
                            viewModel.loadNextPage(page)
                            adapter.notifyItemRangeInserted(viewModel.beerList.size,25)
                        }
                    }
                }
            }
        })
    }
}