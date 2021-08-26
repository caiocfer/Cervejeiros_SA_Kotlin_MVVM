package com.ferreiracaio.punkapimvvm.presentation.beerfavoritelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreiracaio.punkapimvvm.data.db.BeerDatabase
import com.ferreiracaio.punkapimvvm.data.repository.BeerRepository
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import kotlinx.coroutines.launch

class BeerFavoriteListViewModel(
    private val repository: BeerRepository
) : ViewModel() {

    private val _allBeersEvent = MutableLiveData<List<BeerResponseItem>>()
    val allBeersEvent: LiveData<List<BeerResponseItem>>
        get() = _allBeersEvent

    fun getBeersFromDatabase() = viewModelScope.launch {
        _allBeersEvent.postValue(repository.getAllBeersFromDatabase())
    }




}