package com.fresco.beerbrewery.beer.ui.beerDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fresco.beerbrewery.R
import com.fresco.beerbrewery.beer.listeners.IngredientClickListener
import com.fresco.beerbrewery.beer.model.BeerItem
import com.fresco.beerbrewery.beer.model.Hop
import com.fresco.beerbrewery.beer.ui.BeerActivity
import com.fresco.beerbrewery.beer.ui.adapter.MaltHopAdapter
import com.fresco.beerbrewery.common.util.Constants
import com.fresco.beerbrewery.databinding.BeerDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailsFragment : Fragment(), IngredientClickListener {

    private lateinit var binding: BeerDetailsFragmentBinding
    private var beerItem: BeerItem? = null

    private val hopAdapter: MaltHopAdapter by lazy {
        MaltHopAdapter(this)
    }
    private val maltAdapter: MaltHopAdapter by lazy {
        MaltHopAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.beer_details_fragment, container, false
        )
        setupUi()
        return binding.root
    }

    override fun onWeighClick(hopMalts: Hop) {
        val action =
            BeerDetailsFragmentDirections.actionBeerDetailsFragmentToBeerWeighFragment(hopMalts)
        findNavController().navigate(action)
    }

    private fun setupUi() {
        (activity as BeerActivity).supportActionBar?.title = getString(R.string.beer_details)
        (activity as BeerActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        beerItem = arguments?.getParcelable(Constants.KEY_BEER_ITEM)
        beerItem?.let {
            hopAdapter.updateMaltOrHops(beerItem?.ingredients?.hops)
            maltAdapter.updateMaltOrHops(beerItem?.ingredients?.malt)
        }
        binding.beer = beerItem
        binding.hopadapter = hopAdapter
        binding.maltadapter = maltAdapter
    }
}