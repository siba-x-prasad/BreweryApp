package com.fresco.beerbrewery.beer.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.fresco.beerbrewery.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.findNavController(R.id.nav_host_fragment).navigateUp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}