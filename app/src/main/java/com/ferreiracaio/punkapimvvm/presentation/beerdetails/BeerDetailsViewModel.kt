package com.ferreiracaio.punkapimvvm.presentation.beerdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreiracaio.punkapimvvm.data.repository.BeerRepository
import com.ferreiracaio.punkapimvvm.data.response.BeerResponseItem
import kotlinx.coroutines.launch

class BeerDetailsViewModel(private val repository: BeerRepository):ViewModel() {

    val isFavoriteLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun getMalt(beer: BeerResponseItem):String{
        var malt: String = ""
        for(malts in beer.ingredients.malt){
            malt += "${malts.name} \nAmount: ${malts.amount.value} ${malts.amount.unit}\n"
        }
        return malt
    }

    fun getHops(beer:BeerResponseItem):String{
        var hop:String = ""
        for(hops in beer.ingredients.hops){
            hop += "${hops.name}\n" +
                    "${hops.amount.value} " +
                    "${hops.amount.unit}\n" +
                    "Add: ${hops.add}\n" +
                    "Attribute: ${hops.attribute}\n"
        }
        return hop
    }

    fun getYeast(beer: BeerResponseItem):String = beer.ingredients.yeast

    fun insertBeer(beer: BeerResponseItem) = viewModelScope.launch {
        try{
            val insertedBeer = repository.insertBeer(beer)
        }catch (ex:Exception){
            Log.d("TAG", "insertBeer: $ex")
        }
    }

    fun deleterBeer(beer: BeerResponseItem) = viewModelScope.launch {
        try{
            repository.deleteBeer(beer.id)
        }catch (ex:Exception){
            Log.d("TAG", "deleterBeer: $ex")
        }
    }

    //    fun isFavorite(beer: BeerResponseItem):Boolean{
//        Log.d("TAG", "isFavorite: ${repository.getBeerById(beer.id)}")
////        isFavoriteLiveData.value = repository.getBeerById(beer.id) != null
//        if(repository.getBeerById(beer.id) != null){
//            isFavoriteLiveData.value = true
//            Log.d("TAG", "isFavorite: $isFavoriteLiveData.value")
//        }else{
//            isFavoriteLiveData.value = false
//            Log.d("TAG", "isFavorite: $isFavoriteLiveData.value")
//
//        }
//
//        return isFavoriteLiveData.value!!
//    }
    fun isFavorite(beer: BeerResponseItem):Boolean {
        Log.d("TAG", "Called")
        return repository.getBeerById(beer.id) != null
    }








}