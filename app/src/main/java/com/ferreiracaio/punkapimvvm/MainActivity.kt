package com.ferreiracaio.punkapimvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.ferreiracaio.punkapimvvm.data.db.BeerDAO
import com.ferreiracaio.punkapimvvm.data.db.BeerDatabase
import com.ferreiracaio.punkapimvvm.data.repository.BeerRepository
import com.ferreiracaio.punkapimvvm.data.repository.DatabaseDataSource
import com.ferreiracaio.punkapimvvm.presentation.beerdetails.BeerDetailsViewModel
import com.ferreiracaio.punkapimvvm.presentation.beerfavoritelist.BeerFavoriteListFragment
import com.ferreiracaio.punkapimvvm.presentation.beerlist.BeerListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this,R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.itemRippleColor = null
        startFragment()


        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.beerListFragment->{
                    val homeFragment = BeerListFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,homeFragment)
                        .commit()
                }
                R.id.beerFavoriteListFragment->{
                    val favoriteFragment = BeerFavoriteListFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,favoriteFragment)
                        .commit()
                }

                else -> {
                    return@setOnItemSelectedListener false
                }
            }
            return@setOnItemSelectedListener true


        }


    }

    private fun startFragment() {
        val homeFragment = BeerListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView,homeFragment)
            .commit()
    }


}