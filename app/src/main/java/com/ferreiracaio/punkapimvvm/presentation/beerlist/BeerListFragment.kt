package com.ferreiracaio.punkapimvvm.presentation.beerlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreiracaio.punkapimvvm.R
import kotlinx.android.synthetic.main.beer_list_adapter.*
import kotlinx.android.synthetic.main.beer_list_fragment.*

class BeerListFragment : Fragment(R.layout.beer_list_fragment) {



    private lateinit var viewModel: BeerListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeerListViewModel::class.java)
        viewModel.beersLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                with(recyclerBeerList){
                    layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
                    setHasFixedSize(true)
                    adapter = BeerListAdapter(it)
                }
            }

        })
        viewModel.getBeers()

        Log.d("TAG", "onViewCreated: ${viewModel.beersLiveData.value?.get(0)}")
    }

}