package com.ferreiracaio.punkapimvvm.presentation.beerlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.presentation.beerdetails.BeerDetailsActivity
import kotlinx.android.synthetic.main.beer_list_fragment.*

class BeerListFragment : Fragment(R.layout.beer_list_fragment) {


    private lateinit var viewModel: BeerListViewModel
    var page:Int=1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeerListViewModel::class.java)
        val recyclerView = recyclerBeerList
        val adapter = BeerListAdapter(viewModel.beerList){
            val intent = Intent(requireContext(),BeerDetailsActivity::class.java)
            startActivity(intent)
        }

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

//        viewModel.isLoading.observe(viewLifecycleOwner,{isLoading->
//            if(isLoading){
//                Log.d("TAG", "onViewCreated: $isLoading")
//                progressBeerList.visibility = View.VISIBLE
//            }else{
//                progressBeerList.visibility = View.GONE
//            }
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
                if(dy>0){
                    val visibleItemCount = linearLayoutManager.childCount;
                    val totalItemCount = linearLayoutManager.itemCount;
                    val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if((visibleItemCount+pastVisibleItems) >= totalItemCount && page<=13){
                        page += 1
                        viewModel.loadNextPage(page)
                        adapter.notifyItemRangeInserted(viewModel.beerList.size,25)
                    }
                }
            }
        })
    }
}