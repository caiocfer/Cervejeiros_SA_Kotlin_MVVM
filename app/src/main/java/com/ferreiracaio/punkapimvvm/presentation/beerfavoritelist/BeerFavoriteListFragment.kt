package com.ferreiracaio.punkapimvvm.presentation.beerfavoritelist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreiracaio.punkapimvvm.R
import com.ferreiracaio.punkapimvvm.data.db.BeerDAO
import com.ferreiracaio.punkapimvvm.data.db.BeerDatabase
import com.ferreiracaio.punkapimvvm.data.repository.BeerRepository
import com.ferreiracaio.punkapimvvm.data.repository.DatabaseDataSource
import com.ferreiracaio.punkapimvvm.presentation.beerlist.BeerListAdapter
import kotlinx.android.synthetic.main.beer_favorite_list_fragment.*

class BeerFavoriteListFragment : Fragment(R.layout.beer_favorite_list_fragment) {

//    private lateinit var viewModel: BeerFavoriteListViewModel
    private lateinit var beerAdapter:BeerListAdapter

    private val viewModel: BeerFavoriteListViewModel by viewModels {
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val beerDAO:BeerDAO = BeerDatabase.getInstace(requireContext()).beerDAO()
                val repository: BeerRepository = DatabaseDataSource(beerDAO)
                return BeerFavoriteListViewModel(repository) as T
            }

        }
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(BeerFavoriteListViewModel::class.java)
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false)

        viewModel.getBeersFromDatabase()
        viewModel.allBeersEvent.observe(viewLifecycleOwner){beers->
            val beerListAdapter = BeerListAdapter(beers)
            with(recyclerFavoriteBeerList){
                layoutManager = linearLayoutManager
                setHasFixedSize(true)
                adapter = beerListAdapter
            }



        }
    }



}