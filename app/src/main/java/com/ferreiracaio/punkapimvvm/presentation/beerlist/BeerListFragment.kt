package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreiracaio.punkapimvvm.R
import kotlinx.android.synthetic.main.beer_list_fragment.*

class BeerListFragment : Fragment(R.layout.beer_list_fragment) {

    private lateinit var viewModel: BeerListViewModel
    private lateinit var beerAdapter: BeerListAdapter
    var page:Int=1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeerListViewModel::class.java)
        beerAdapter = BeerListAdapter(viewModel.beerList)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        recyclerBeerList.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = beerAdapter
        }



        observeLoadingState()
        observeBeerList()
        viewModel.getBeers()

        searchBeer.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.getBeerSearch(query)
                    beerAdapter.notifyDataSetChanged()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchBeer.setOnCloseListener {
            viewModel.getBeers()
            beerAdapter.notifyDataSetChanged()
            true
        }

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
                            beerAdapter.notifyItemRangeInserted(viewModel.beerList.size,25)
                        }
                    }
                }
            }
        })
    }

    private fun observeBeerList(){
        viewModel.beersLiveData.observe(viewLifecycleOwner,{beerList->
            beerList.let {
                beerAdapter.notifyItemRangeInserted(viewModel.beerList.size,25)
            }
        })
        viewModel.getBeers()
    }

    private fun observeLoadingState(){
        viewModel.isLoading.observe(viewLifecycleOwner,{isLoading->
            if(isLoading){
                progressBeerList.visibility = View.VISIBLE
            }else{
                progressBeerList.visibility = View.GONE
            }
        })
    }


}