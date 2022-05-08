package com.fresco.beerbrewery.beer.ui.beerDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fresco.beerbrewery.R
import com.fresco.beerbrewery.beer.listeners.IngredientClickListener
import com.fresco.beerbrewery.beer.model.BeerItem
import com.fresco.beerbrewery.beer.model.Hop
import com.fresco.beerbrewery.beer.ui.adapter.MaltHopAdapter
import com.fresco.beerbrewery.beer.ui.weigh.SharedBeerViewModel
import com.fresco.beerbrewery.common.util.Constants
import com.fresco.beerbrewery.databinding.BeerDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailsFragment : Fragment(), IngredientClickListener {

    private val viewModel by viewModels<BeerDetailsViewModel>()
    private val sharedBeerViewModel: SharedBeerViewModel by viewModels()
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

        beerItem = arguments?.getParcelable(Constants.KEY_BEER_ITEM)
        beerItem?.let {
            hopAdapter.updateItems(beerItem?.ingredients?.hops)
            maltAdapter.updateItems(beerItem?.ingredients?.malt)
        }

        val view: View = binding.root
        binding.viewModel = viewModel
        binding.beer = beerItem
        binding.hopadapter = hopAdapter
        binding.maltadapter = maltAdapter
        return view
    }

    override fun onWeighClick(hopMalts: Hop) {
        hopMalts.isWeighed = true
        if (hopMalts.attribute.isNullOrEmpty() && hopMalts.add.isNullOrEmpty()) {
            beerItem?.ingredients?.malt?.set(hopMalts.id, hopMalts)
        } else {
            beerItem?.ingredients?.hops?.set(hopMalts.id, hopMalts)
        }
        sharedBeerViewModel.updateBeerItem(beerItem)
        val action =
            BeerDetailsFragmentDirections.actionBeerDetailsFragmentToBeerWeighFragment(hopMalts)
        findNavController().navigate(action)
    }
}